package org.firstinspires.ftc.teamcode.subsystems;

import com.technototes.vision.hardware.Camera;
import com.technototes.vision.subsystem.PipelineSubsystem;

import org.opencv.core.Mat;

import java.util.function.Supplier;

public class VisionSubsystem extends PipelineSubsystem implements Supplier<Integer> {

  public VisionSubsystem(Camera c) {
    super(c);
  }

  @Override
  public Integer get() {
    return null;
  }

  @Override
  public Mat processFrame(Mat input) {
    return null;
  }
}