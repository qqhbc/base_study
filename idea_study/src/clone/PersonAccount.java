package clone;

public class PersonAccount implements Cloneable {
    private User user;
    private Integer account;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        PersonAccount personAccount = (PersonAccount) super.clone();
        User user = (User) personAccount.getUser().clone();
        personAccount.setUser(user);
        return personAccount;
    }

    @Override
    public String toString() {
        return "PersonAccount{" +
                "name='" + user.getName() + '\'' +
                ", account=" + account +
                '}';
    }
}
