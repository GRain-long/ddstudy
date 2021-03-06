package com.ddstudy.state;

/**
 * @Classname OpenState
 * @Description 开门状态
 * @Date 2020/6/20
 * @Author Grain Rain
 */
public class OpenState implements State {

    private Door door;

    @Override
    public void open() {
        System.out.println("The door is opened");
    }

    @Override
    public void close() {
        System.out.println("Close the door");
        door.setState(Door.CLOSED_STATE);
    }
}
