public class Participant extends User{
    public Participant(String username, String password) {
        super(username, password);
    }

    @Override
    public UserType getUserType() {
        return UserType.PARTICIPANT;
    }
}
