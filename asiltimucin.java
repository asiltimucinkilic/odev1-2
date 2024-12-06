package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfigurator;


public class asiltimucin extends CommandBase {

    private Joystick joystick;
    private TalonFX left_motor;
    private TalonFX right_motor;
    private TalonFX feeder;
    private TalonFX intake;
    private TalonFXConfiguration right_motor_config;
    private TalonFXConfiguration left_motor_config;
    private TalonFXConfigurator motor_configurator;




    @Override
    public void initialize() {

        joystick = new Joystick(0);
        left_motor = new TalonFX(1);
        right_motor= new TalonFX(2);
        feeder= new TalonFX(3);
        intake= new TalonFX(4);
        right_motor_config= new TalonFXConfiguration();
        left_motor_config= new TalonFXConfiguration();
        motor_configurator= new TalonFXConfigurator(null);

        

        left_motor.getConfigurator().apply(left_motor_config);
        right_motor.getConfigurator().apply(right_motor_config);
        motor_configurator.apply(left_motor_config);
        motor_configurator.apply(right_motor_config);
    }

    @Override 
    public void execute() {

        double forward = joystick.getY();
        double turn= joystick.getX();

        if (joystick.getRawButtonPressed(1)) {

            left_motor.set(1);
            right_motor.set(1);
        }

        else if (joystick.getRawButtonPressed(2)) {

            left_motor.set(-1);
            right_motor.set(-1);
        }

        else {

            double left_motor_speed = forward + turn;
            double right_motor_speed = forward - turn;

            left_motor.set(left_motor_speed);
            right_motor.set(right_motor_speed);
        }

        if(joystick.getTriggerPressed()) {

            feeder.set(0.7);
            intake.set(0.7);
        }
        else if (joystick.getTopPressed()) {

            feeder.set(-0.7);
            intake.set(-0.7);
        }

        else {

            feeder.set(0);
            intake.set(0);




        }
    
    }


}
