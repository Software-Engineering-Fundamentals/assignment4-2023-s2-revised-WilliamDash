package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Department in an organisation
 */
public class Department {

    private String name;

    private ArrayList<String> ID1 = new ArrayList<String>();

    private ArrayList<String> temp1 = new ArrayList<String>();

    private ArrayList<int[]> employee = new ArrayList<int[]>();

    private int ID2;

    public String getID() {
        return ID2;
    }

    public void setID(int ID) {
        this.ID2 = ID;
    }

    public boolean checkEmployee(int employeeID) {
        // Iterate through employees
        for (int i = 0; i < this.employee.size(); i++) {
            check = this.employee.get(i);
            // Check if current employee is the one
            if (check == employeeID) {
                System.out.println("Employee exists");
                return true;
            }
        }
        // Employee was not found
        System.out.println("Employee doesn't exist");
        return false;
    }

    public void assignNew(string temp2, int ID) {
        this.temp1.add(temp2);
        this.ID1.add(ID);
    }

}
