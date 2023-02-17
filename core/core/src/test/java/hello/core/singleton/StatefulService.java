package hello.core.singleton;

public class StatefulService {

    private int price; // 상태를 유지하는 필드

    //무상태로 설계를 하지 않음
    public int order(String name, int price){
        System.out.println("name = " + name + "price = " + price);
        return price;
    }

    //무상태로 설계
//    public void order(String name, int price){
//        System.out.println("name = " + name + "price = " + price);
//        this.price = price;
//    }

    public int getPrice(){
        return price;
    }


}
