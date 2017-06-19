package pl.majcher.model;

public class Follow {
    private User followed;
    private User follower;

    public Follow(User follower, User followed) {
        this.followed = followed;
        this.follower = follower;
    }

    public User getFollowed() {
        return followed;
    }

    public void setFollowed(User followed) {
        this.followed = followed;
    }

    public User getFollower() {
        return follower;
    }

    public void setFollower(User follower) {
        this.follower = follower;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Follow follow = (Follow) o;

        if (!followed.equals(follow.followed)) return false;
        return follower.equals(follow.follower);
    }

    @Override
    public int hashCode() {
        int result = followed.hashCode();
        result = 31 * result + follower.hashCode();
        return result;
    }
}
