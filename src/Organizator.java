public class Organizator extends User{
    public Organizator(String username, String password) {
        super(username, password);
    }

    @Override
    public UserType getUserType() {
        return UserType.ORGANIZATOR;
    }
}
