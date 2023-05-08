package org.ficko.models;

public class CredentialProviders {
    public static ICredentialsProvider bot() {
        return new ICredentialsProvider() {
            @Override
            public String getLogin() {
                return "botS23AT26";
            }

            @Override
            public String getPassword() {
                return "autotests2023";
            }
        };
    }
}
