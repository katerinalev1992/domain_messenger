package com.klevytska.dm.utils;

import static javax.naming.directory.SearchControls.SUBTREE_SCOPE;

import java.util.Hashtable;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import com.sun.jndi.ldap.LdapCtxFactory;

@SuppressWarnings("restriction")
@ApplicationScoped
public class ActiveDirectoryAuth {

    @Inject
    private Logger logger;

    private String domainName = "GK-DOMAIN";
    private String serverName = "FAWN";
    private String group = "cn: LOC_Ukraine";



    public ActiveDirectoryAuth() {
    }

    public ActiveDirectoryAuth(String domainName, String serverName) {
        super();
        this.domainName = domainName;
        this.serverName = serverName;
    }

    //TODO Add null checks
  /*public boolean checkNullNamePassword (String username, String password){
    if (username.isEmpty() || password.isEmpty()){
      return false;
    }
    else{
      return true;
    }
  }
  */

    public boolean checkIfUserBelongsTOGroup(String username, String password) {
        Hashtable<String, String> loginProperties = new Hashtable<String, String>();
        //checkNullNamePassword(username, password);
        String principalName = username + "@" + domainName;
        loginProperties.put(Context.SECURITY_PRINCIPAL, principalName);
        loginProperties.put(Context.SECURITY_CREDENTIALS, password);
        DirContext context;
        boolean belongs = false;
        try {
            logger.info("Trying to connect to" + "ldap://" + serverName + "." + domainName + '/');
            context = LdapCtxFactory.getLdapCtxInstance("ldap://" + serverName + "." + domainName + '/', loginProperties);
            System.out.println("Authentication succeeded!");

            //TODO Make a clear check for group
            SearchControls controls = new SearchControls();
            controls.setSearchScope(SUBTREE_SCOPE);
            NamingEnumeration<SearchResult> renum = context.search(toDC(domainName),
                    "(& (userPrincipalName=" + principalName + ")(objectClass=user))", controls);
            if (!renum.hasMore()) {
                System.out.println("Cannot locate user information for " + username);
                return false;
            }
            SearchResult result = renum.next();
            logger.info("RESULT: " + result);
            Attribute memberOf = result.getAttributes().get("memberOf");
            if (memberOf != null) {// null if this user belongs to no group at all
                for (int i = 0; i < memberOf.size(); i++) {
                    Attributes atts = context.getAttributes(memberOf.get(i).toString(), new String[] { "CN" });
                    Attribute att = atts.get("CN");
                    logger.info(att.toString());
                    if (att.toString().equals(group)) {
                        belongs = true;
                    }
                }
            }
            context.close();


        } catch (AuthenticationException a) {
            logger.severe("Authentication failed: " + a);
        } catch (NamingException e) {
            logger.severe("Failed to bind to LDAP / get account information: " + e);
        }

        return belongs;
    }
    private String toDC(String domainName) {
        StringBuilder buf = new StringBuilder();
        for (String token : domainName.split("\\.")) {
            if (token.length() == 0)
                continue; // defensive check
            if (buf.length() > 0)
                buf.append(",");
            buf.append("DC=").append(token);
        }
        return buf.toString();
    }

}
