package net.barry.italker.push;

/**
 * Created by Administrator on 2017/8/23.
 */

public class UserService implements IUserService{
    public String search(int hashCode)
    {
        return "User:"+hashCode;
    }
}
