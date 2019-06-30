package clone;

public class PersonAccountFactory {
    private PersonAccount personAccount;

    public PersonAccount getPersonAccount(String name) throws CloneNotSupportedException {
        if (personAccount == null) {
            personAccount = new PersonAccount();
            User user = new User();
            user.setName(name);
            personAccount.setAccount(1000);
            personAccount.setUser(user);
        }
        return (PersonAccount) personAccount.clone();
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        String name = "hty";
        PersonAccountFactory personAccountFactory = new PersonAccountFactory();
        PersonAccount hty = personAccountFactory.getPersonAccount(name);
        System.out.println(hty);
        hty.setAccount(100000);
        System.out.println(hty);
        PersonAccount personAccount = personAccountFactory.getPersonAccount(name);
        User user = new User();
        user.setName("me");
        personAccount.setUser(user);
        System.out.println(personAccount);
        PersonAccount personAccount1 = personAccountFactory.getPersonAccount("me");
        System.out.println(personAccount1);

    }
}
