// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfigurator;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
 private Joystick joystick;
    private TalonFX left_motor;
    private TalonFX right_motor;
    private TalonFX feeder;
    private TalonFX intake;
    private TalonFXConfiguration right_motor_config;
    private TalonFXConfiguration left_motor_config;
    private TalonFXConfigurator motor_configurator;
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {}

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
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

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {

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

    



  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
  
