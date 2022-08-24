package hello.core.singletone;

public class singletoneService {

    private singletoneService() {} //new로 인한 외부호출 막기위한 빈 private 생성자
    //1. 자바가 뜨면 static영역에 instance 하나 생성해서 올려둠
    private static final singletoneService instance = new singletoneService();
    //2. 이제 인스턴스를 생성할 수 있는 방법은 getInstance()로 제한됨
    public static singletoneService getInstance() {
        return instance;
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
