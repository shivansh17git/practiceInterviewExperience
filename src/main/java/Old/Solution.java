package Old;/*
Tesco has around 3200 stores and more than 10% of the stores have around 800 colleagues each.


In a store, a colleague can work for multiple departments. Here are shifts of a colleague in various departments:

In Bakery department: From 8 to 10
In Checkout department: From 10 to 12
In Dairy department: From 14 to 19

Given the above split of shifts, provide an API/method to return the different shifts of a colleague for the day after merging contiguous shifts. This will be exposed to the colleague in different UI and help them plan their day accordingly.

His shift timings in this case are 8 to 12 and 14 to 19.

8.15, 8.30

 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    public static Map<Integer,EmployeeShift> employeeShiftMap = new HashMap<>();

    public static void main(String[] args) {
        List<Shift> shifts = new ArrayList<>();
        shifts.add(new Shift(new ShiftTime(10,0),new ShiftTime(12,0)));
        shifts.add(new Shift(new ShiftTime(14,0),new ShiftTime(19,0)));
        shifts.add(new Shift(new ShiftTime(19,0),new ShiftTime(19,15)));
        shifts.add(new Shift(new ShiftTime(19,35),new ShiftTime(19,35)));
        shifts.add(new Shift(new ShiftTime(8,0),new ShiftTime(10,0)));
        EmployeeShift employeeShift = new EmployeeShift(1,shifts);
        employeeShiftMap.put(1,employeeShift);
        System.out.println(getMergedShift(1));
    }

    public static List<Shift> getMergedShift(int employeeId) {
        EmployeeShift ememployeeShift = employeeShiftMap.get(employeeId);
        return mergeShift(ememployeeShift.getShifts());
    }

    private static class ShiftComparator implements Comparator<Shift> {
        @Override
        public int compare(Shift s1, Shift s2) {
                ShiftTime start1 = s1.getStartTime();
                ShiftTime start2 = s2.getStartTime();
                if(start1.getHour() != start2.getHour())
                    return start1.getHour() - start2.getHour();
                return start1.getMinute() - start2.getMinute();
        }
    }

    private static List<Shift> mergeShift(List<Shift> shifts) {
        if(shifts.isEmpty())
            return new ArrayList<>();
        List<Shift> auxList = new ArrayList<>(shifts);
        auxList.sort(new ShiftComparator());
        List<Shift> outPutShifts = new ArrayList<>();
        outPutShifts.add(auxList.get(0));
        for(int i=1; i < auxList.size(); i++) {
            Shift currentShift = auxList.get(i);
            Shift prevShift = outPutShifts.get(outPutShifts.size() - 1);
            if(currentShift.startTime.equals(prevShift.endTime)) {
                prevShift.setEndTime(currentShift.endTime);
            }
            else {
                outPutShifts.add(currentShift);
            }
        }
        return outPutShifts;
    }

    private static class EmployeeShift {
        int id;
        List<Shift> shifts;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<Shift> getShifts() {
            return shifts;
        }

        public void setShifts(List<Shift> shifts) {
            this.shifts = shifts;
        }

        public EmployeeShift(int id, List<Shift> shifts) {
            this.id = id;
            this.shifts = shifts;
        }
    }

    private static class Shift {
        private ShiftTime startTime;
        private ShiftTime endTime;

        public ShiftTime getStartTime() {
            return startTime;
        }

        public void setStartTime(ShiftTime startTime) {
            this.startTime = startTime;
        }

        public ShiftTime getEndTime() {
            return endTime;
        }

        public void setEndTime(ShiftTime endTime) {
            this.endTime = endTime;
        }

        public Shift(ShiftTime startTime, ShiftTime endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public String toString() {
            return "Shift{" +
                    "startTime=" + startTime.getHour() + ":" + startTime.getMinute() +
                    ", endTime=" + endTime.getHour() + ":" + endTime.getMinute() +
                    '}';
        }
    }

    private static class ShiftTime {
        private int hour;
        private int minute;

        public int getHour() {
            return hour;
        }

        public void setHour(int hour) {
            this.hour = hour;
        }

        public int getMinute() {
            return minute;
        }

        public void setMinute(int minute) {
            this.minute = minute;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ShiftTime shiftTime = (ShiftTime) o;
            return hour == shiftTime.hour && minute == shiftTime.minute;
        }

        public ShiftTime(int hour, int minute) {
            this.hour = hour;
            this.minute = minute;
        }
    }
}
