package com.sakksoftware.web.bbrestapioauth2.Service;

import com.sakksoftware.web.bbrestapioauth2.model.Account;
import com.sakksoftware.web.bbrestapioauth2.model.Role;
import com.sakksoftware.web.bbrestapioauth2.repository.AccountRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AccountService implements UserDetailsService {

    private final Log log = LogFactory.getLog(AccountService.class);

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Account> account = accountRepository.findByUsername( s );
        if ( account.isPresent() ) {
            return account.get();
        } else {
            throw new UsernameNotFoundException(String.format("Username[%s] not found", s));
        }
    }

    public Account findAccountByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> account = accountRepository.findByUsername( username );
        if ( account.isPresent() ) {
            return account.get();
        } else {
            throw new UsernameNotFoundException(String.format("Username[%s] not found", username));
        }
    }

    public Account registerUser(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.grantAuthority(Role.ROLE_USER);
        return accountRepository.save( account );
    }

    @Transactional // To successfully remove the date @Transactional annotation must be added
    public boolean removeAuthenticatedAccount() throws UsernameNotFoundException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = findAccountByUsername(username);
        int del = accountRepository.deleteAccountById(account.getId());
        return del > 0;
    }
}
