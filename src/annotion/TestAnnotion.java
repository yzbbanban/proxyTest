package annotion;

/**
 * Created by brander on 2018/7/29
 */
public class TestAnnotion {

    public static void main(String[] args) {
        User user=new User();
        user.setId(1);
        user.setNick("banban");
        user.setPwd("111111");
        System.out.println(AnnotationDealUtil.validate(user));
    }

}
