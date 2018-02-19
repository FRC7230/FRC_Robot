/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team7230.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import org.usfirst.frc.team7230.robot.commands.ExampleCommand;
import org.usfirst.frc.team7230.robot.subsystems.ExampleSubsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.PIDOutput;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {

	//private Encoder
	//enc_0 = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
	//private Encoder
	//enc_1 = new Encoder(1, 1, false, Encoder.EncodingType.k4X);
	private DifferentialDrive m_robotDrive //main
			= new DifferentialDrive(new Spark(0), new Spark(2));
	private DifferentialDrive s_robotDrive //secondary
	= new DifferentialDrive(new Spark(1), new Spark(3));
	private TalonSRX l_motor = new TalonSRX(1); //left
	private TalonSRX r_motor = new TalonSRX(2); //right
	private VictorSPX w_motor = new VictorSPX(3); //west
	private VictorSPX e_motor = new VictorSPX(4); //east
	private Joystick m_stick = new Joystick(0);
	private Joystick m_grab = new Joystick(1);
	private Timer m_timer = new Timer();
	//EncoderPIDSource eSource = new EncoderPIDSource(enc_0,enc_1);
	//EncoderPIDOutput eOutput = new EncoderPIDOutput();
	//private PIDController m_robotPID = new PIDController(1,1,1,1,eSource,eOutput);
	
	SendableChooser<String> autoPositionChooser;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		autoPositionChooser = new SendableChooser<String>();
		autoPositionChooser.addDefault("Left", "Left");
		autoPositionChooser.addObject("Middle", "Middle");
		autoPositionChooser.addObject("Right", "Right");
		SmartDashboard.putData("Robot Position", autoPositionChooser);
		  
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		if(gameData.charAt(0) == 'L')
		{
			System.out.println("The scale is on the left");
			//Put left auto code here
		} else {
			System.out.println("The scale is on the right");
			//Put right auto code here
		}
		
		String choice = autoPositionChooser.getSelected();
		
		System.out.println("The robot is in the [" + choice + "] position.");
		
		m_timer.reset();
		m_timer.start();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		
		}
	

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if(gameData.charAt(0)== 'L' ){//switch is left
			if (autoPositionChooser.getName()=="Left") {
				
			}else if (autoPositionChooser.getName()=="Middle") {
	
			}else if (autoPositionChooser.getName()=="Right") {
				
			}
		}
		else {//switch is right
		if (autoPositionChooser.getName()=="Left") {
			
		}else if (autoPositionChooser.getName()=="Middle") {
			
		}else if (autoPositionChooser.getName()=="Right") {
			
		}
			
		}
	
			// Drive for 1 seconds calibration
			if (m_timer.get() < 1.0) {
				m_robotDrive.arcadeDrive(-0.65, 0.0); 
				s_robotDrive.arcadeDrive(-0.65, 0.0);// drive forwards half speed
		
			}
			 else {
				m_robotDrive.stopMotor();
				s_robotDrive.stopMotor();// stop robot
			
		}
		
		}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		
		}
	

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		double a= (m_stick.getThrottle()+2);
		double Y= m_stick.getY();
		double X=m_stick.getX();
		m_robotDrive.arcadeDrive(a*Y*Y*Y,a*X*X*X);
		s_robotDrive.arcadeDrive(a*Y*Y*Y,a*X*X*X);
		l_motor.set(ControlMode.PercentOutput,m_grab.getY() );
		r_motor.set(ControlMode.PercentOutput,m_grab.getY() );
		l_motor.set(ControlMode.PercentOutput,m_grab.getX() );
		r_motor.set(ControlMode.PercentOutput,-m_grab.getX());
		w_motor.set(ControlMode.Follower, 1);
		e_motor.set(ControlMode.Follower,2);

	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
