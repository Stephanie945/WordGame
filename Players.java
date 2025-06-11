public class Players extends Person {
    private int money;

    public Players(String name) {
        super(name);
        this.money = 1000;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return getName() + " has $" + money;
    }
}
