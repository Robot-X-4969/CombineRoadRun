package com.technototes.library.command;


import com.qualcomm.robotcore.util.ElapsedTime;
import com.technototes.library.subsystem.Subsystem;
import com.technototes.library.subsystem.SubsystemBase;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

/** The root Command class
 * @author Alex Stedman
 */
@FunctionalInterface
public interface Command extends Runnable{

    Map<Command, CommandState> stateMap = new HashMap<>();
    Map<Command, ElapsedTime> timeMap = new HashMap<>();
    Map<Command, Set<Subsystem>> requirementMap = new HashMap<>();



    /** Add requirement subsystems to command
     *
     * @param requirements The subsystems
     * @return this
     */
    default Command addRequirements(Subsystem... requirements) {
        Set<Subsystem> s = requirementMap.putIfAbsent(this, new HashSet<>(Arrays.asList(requirements)));
        if(s != null) s.addAll(Arrays.asList(requirements));
        return this;
    }

    /** Init the command
     *
     */
    default void init(){

    }

    /** Execute the command
     *
     */
    void execute();

    /** Return if the command is finished
     *
     * @return Is command finished
     */
    default boolean isFinished() {
        return true;
    }

    /** End the command
     *
     * @param cancel If the command was cancelled or ended naturally
     */
    default void end(boolean cancel) {

    }

    //run a command after
    default SequentialCommandGroup andThen(Command... c){
        return new SequentialCommandGroup(this, c.length == 1 ? c[0] : new ParallelCommandGroup(c));
    }

    //wait a time
    default SequentialCommandGroup sleep(double sec){
        return andThen(new WaitCommand(sec));
    }
    default SequentialCommandGroup sleep(DoubleSupplier sup){
        return andThen(new WaitCommand(sup));
    }



    //await a condition
    default SequentialCommandGroup until(BooleanSupplier condition) {
        return andThen(new ConditionalCommand(condition));
    }

    //run a command in parallel
    default ParallelCommandGroup alongWith(Command... c){
        Command[] c1 = new Command[c.length+1];
        c1[0] = this;
        System.arraycopy(c, 0, c1, 1, c.length);
        return new ParallelCommandGroup(c1);
    }

    default ParallelDeadlineGroup deadline(Command c){
        return new ParallelDeadlineGroup(this, c);
    }

    default ParallelRaceGroup raceWith(Command... c){
        Command[] c1 = new Command[c.length+1];
        c1[0] = this;
        System.arraycopy(c, 0, c1, 1, c.length);
        return new ParallelRaceGroup(c1);
    }


    /** Creates a conditional command out of this
     *
     * @param condition The condition to run the command under
     * @return the conditional command
     */
    default ConditionalCommand asConditional(BooleanSupplier condition){
        return new ConditionalCommand(condition, this);
    }

    default ParallelRaceGroup withTimeout(double seconds){
        return raceWith(new WaitCommand(seconds));
    }

    default ParallelRaceGroup cancelUpon(BooleanSupplier condition){
        return raceWith(new ConditionalCommand(condition));
    }


    /** Run the commmand
     *
     */
    @Override
    default void run() {
        switch (getState()) {
            case RESET:
                getRuntime().reset();
                setState(CommandState.INITIALIZING);
                return;
            case INITIALIZING:
                init();
                setState(CommandState.EXECUTING);
                //THERE IS NO RETURN HERE SO IT FALLS THROUGH TO POST-INITIALIZATION
                return;
            case EXECUTING:
                execute();
                if(isFinished()) setState(CommandState.FINISHED);
                //allow one cycle to run so other dependent commands can schedule
                return;
            case CANCELLED:
            case FINISHED:
                end(isCancelled());
                setState(CommandState.RESET);
        }
    }

    /** The command state enum
     *
     */
    enum CommandState {
        RESET, INITIALIZING, EXECUTING, FINISHED, CANCELLED
    }


    /** Return the command runtime
     *
     * @return The runtime as an {@link ElapsedTime}
     */
    default ElapsedTime getRuntime() {
        ElapsedTime t = timeMap.putIfAbsent(this, new ElapsedTime());
        return t != null ? t : timeMap.get(this);
    }

    /** Return the command state
     *
     * @return The state as an {@link CommandState}
     */
    default CommandState getState() {
        return stateMap.getOrDefault(this, CommandState.RESET);
    }
    default Command setState(CommandState s) {
        stateMap.put(this, s);
        return this;
    }

    /** Return the subsystem requirements for this command
     *
     * @return The {@link SubsystemBase} requirements
     */
    default Set<Subsystem> getRequirements() {
        requirementMap.putIfAbsent(this, new HashSet<>());
        return requirementMap.get(this);
    }



    default boolean justFinished(){
        return getState() == CommandState.FINISHED || getState() == CommandState.CANCELLED;
    }
    default boolean justFinishedNoCancel(){
        return getState() == CommandState.FINISHED;
    }
    default boolean justStarted() {
        return getState() == CommandState.INITIALIZING;
    }


    default boolean isRunning(){
        return getState() != CommandState.RESET ;
    }

    default boolean isCancelled(){
        return getState() == CommandState.CANCELLED ;
    }


    default void cancel(){
        if(isRunning()) setState(CommandState.CANCELLED);
    }

    static void clear(){
        stateMap.clear();
        timeMap.clear();
        requirementMap.clear();
    }


}
