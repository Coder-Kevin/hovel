分析：当原油价格上涨时，空方伤心，多方局兴；当油价下跌时，空方局兴，多方伤心。本实例中的抽象目标（Observable)类在 Java 中已经定义，
可以直接定义其子类，即原油期货（OilFutures)类，它是具体目标类，该类中定义一个 SetPriCe(float price) 方法，当原油数据发生变化时调
用其父类的 notifyObservers(Object arg) 方法来通知所有观察者；另外，本实例中的抽象观察者接口（Observer）在 Java 中已经定义，只要
定义其子类，即具体观察者类（包括多方类 Bull 和空方类 Bear），并实现 update(Observable o,Object arg) 方法即可。图 5 所示是其结构图。