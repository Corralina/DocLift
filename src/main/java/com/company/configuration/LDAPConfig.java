package com.company.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import java.util.Hashtable;


@Configuration
public class LDAPConfig {
    @Value("${spring.ldap.urls}")
    private String url;
    @Value("${spring.ldap.context.factory}")
    private String factory;
    @Value("${spring.ldap.security.principal.domen}")
    private String principalDomen;
    @Value("${spring.ldap.security.principal.username}")
    private String principalUsername;
    @Value("${spring.ldap.security.credentials}")
    private String credentials;

    public NamingEnumeration<SearchResult> LDAPConnect(String returnedAtts[]) throws NamingException {
        Hashtable<String, String> ldapEnv = new Hashtable<String, String>(11);
        ldapEnv.put(Context.INITIAL_CONTEXT_FACTORY, factory);
        ldapEnv.put(Context.PROVIDER_URL,  url);
        ldapEnv.put(Context.SECURITY_AUTHENTICATION, "simple");
        ldapEnv.put(Context.SECURITY_PRINCIPAL, principalDomen + "\\Администратор");
        ldapEnv.put(Context.SECURITY_CREDENTIALS, credentials);
        InitialDirContext ldapContext = new InitialDirContext(ldapEnv);
        // Create the search controls
        SearchControls searchCtls = new SearchControls();
        // Specify the attributes to return
//        All Attribute
//        String returnedAtts[]={"*"};
        searchCtls.setReturningAttributes(returnedAtts);
        // Specify the search scope
        searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        // specify the LDAP search filter
        String searchFilter = "(objectClass=person)";
        // Specify the Base for the search
        String searchBase = "ou=personal,dc=7AA,dc=COURT,dc=LOCAL";

        return ldapContext.search(searchBase, searchFilter, searchCtls);
    }
}
