/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Slider;

/**
 *
 * @author dell
 */
public class SliderStatus {
    int statusID;
    String statusName;

    public SliderStatus(int statusID, String statusName) {
        this.statusID = statusID;
        this.statusName = statusName;
    }

    public SliderStatus() {
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
    
}
