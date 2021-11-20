package org.firstinspires.ftc.teamcode.hardware;

import androidx.annotation.NonNull;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.BNO055IMUImpl;
import com.qualcomm.robotcore.hardware.HardwareDevice;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.MagneticFlux;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Quaternion;
import org.firstinspires.ftc.robotcore.external.navigation.Temperature;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;

public class MockIMU implements BNO055IMU, HardwareDevice {
    @Override
    public boolean initialize(@NonNull Parameters parameters) {
        return false;
    }

    @NonNull
    @Override
    public Parameters getParameters() {
        return new BNO055IMU.Parameters();
    }

    @Override
    public Manufacturer getManufacturer() {
        return null;
    }

    @Override
    public String getDeviceName() {
        return "eee";
    }

    @Override
    public String getConnectionInfo() {
        return null;
    }

    @Override
    public int getVersion() {
        return 0;
    }

    @Override
    public void resetDeviceConfigurationForOpMode() {

    }

    @Override
    public void close() {

    }

    @Override
    public Orientation getAngularOrientation() {
        return new Orientation();
    }

    @Override
    public Orientation getAngularOrientation(AxesReference reference, AxesOrder order, org.firstinspires.ftc.robotcore.external.navigation.AngleUnit angleUnit) {
        return new Orientation();
    }

    @Override
    public Acceleration getOverallAcceleration() {
        return new Acceleration();
    }

    @Override
    public AngularVelocity getAngularVelocity() {
        return new AngularVelocity();
    }

    @Override
    public Acceleration getLinearAcceleration() {
        return new Acceleration();
    }

    @Override
    public Acceleration getGravity() {
        return new Acceleration();
    }

    @Override
    public Temperature getTemperature() {
        return new Temperature();
    }

    @Override
    public MagneticFlux getMagneticFieldStrength() {
        return null;
    }

    @Override
    public Quaternion getQuaternionOrientation() {
        return null;
    }

    @Override
    public Position getPosition() {
        return null;
    }

    @Override
    public Velocity getVelocity() {
        return null;
    }

    @Override
    public Acceleration getAcceleration() {
        return null;
    }

    @Override
    public void startAccelerationIntegration(Position initialPosition, Velocity initialVelocity, int msPollInterval) {

    }

    @Override
    public void stopAccelerationIntegration() {

    }

    @Override
    public SystemStatus getSystemStatus() {
        return null;
    }

    @Override
    public SystemError getSystemError() {
        return null;
    }

    @Override
    public CalibrationStatus getCalibrationStatus() {
        return null;
    }

    @Override
    public boolean isSystemCalibrated() {
        return false;
    }

    @Override
    public boolean isGyroCalibrated() {
        return false;
    }

    @Override
    public boolean isAccelerometerCalibrated() {
        return false;
    }

    @Override
    public boolean isMagnetometerCalibrated() {
        return false;
    }

    @Override
    public CalibrationData readCalibrationData() {
        return null;
    }

    @Override
    public void writeCalibrationData(CalibrationData data) {

    }

    @Override
    public byte read8(Register register) {
        return 0;
    }

    @Override
    public byte[] read(Register register, int cb) {
        return new byte[0];
    }

    @Override
    public void write8(Register register, int bVal) {

    }

    @Override
    public void write(Register register, byte[] data) {

    }
}
