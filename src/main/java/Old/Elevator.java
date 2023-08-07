package Old;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;


public class Elevator {

    RunningState currentState;

    int currentFloor;
    int maxFloor = Integer.MAX_VALUE;
    int minFloor = Integer.MIN_VALUE;

    Queue<Instruction> upInstructions;
    Queue<Instruction> downInstructions;

    Set<Integer> instructionPending;

    Elevator(int maxFloor , int minFloor) {
        currentFloor = 0;
        currentState = RunningState.STILL;
        upInstructions = new PriorityQueue<>((c1,c2) -> c2.destination - c1.destination);
        downInstructions = new PriorityQueue<>((c1,c2) -> c1.destination - c2.destination);
        this.maxFloor = maxFloor;
        this.minFloor = minFloor;
    }

    public void addInstruction(Instruction instruction) {

        if(instructionPending.contains(instruction.destination))
            return;

        instructionPending.add(instruction.destination);

        if(currentState.equals(RunningState.UP))

        if(currentFloor < instruction.destination) {
            upInstructions.add(instruction);
        }
        else if(currentFloor > instruction.destination) {
            downInstructions.add(instruction);
        }

        startElevator();
    }

    private void startElevator() {
        if(RunningState.STILL.equals(currentState)) {
            if(!upInstructions.isEmpty())
                moveUp();

            if(!downInstructions.isEmpty())
                moveDown();
        }
    }

    private void move(RunningState runningState , Queue<Instruction> instructions) {
        currentState = runningState;
        while(!instructions.isEmpty()) {
            Instruction instruction = instructions.poll();
            System.out.println("Halt At Floor="+ instruction.destination);
            currentFloor = instruction.destination;
            instructionPending.remove(instruction);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                currentFloor = 0;
                currentState = RunningState.STILL;
            }
        }
        currentState = RunningState.STILL;
    }

    private void moveUp() {
        move(RunningState.UP , upInstructions);
    }


    private void moveDown() {
        move(RunningState.DOWN , downInstructions);
    }


}
