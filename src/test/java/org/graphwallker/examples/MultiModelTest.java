package org.graphwallker.examples;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import org.graphwalker.conditions.EdgeCoverage;
import org.graphwalker.examples.modelAPI.Model_A;
import org.graphwalker.examples.modelAPI.Model_B;
import org.graphwalker.examples.modelAPI.Model_C;
import org.graphwalker.examples.modelAPI.Model_D;
import org.graphwalker.exceptions.StopConditionException;
import org.graphwalker.generators.RandomPathGenerator;
import org.graphwalker.multipleModels.ModelHandler;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MultiModelTest {

  @Test
  public void randomAllModels() throws InterruptedException, StopConditionException, URISyntaxException {
    ModelHandler modelhandler = new ModelHandler();

    // Get the model from resources
    URL url_A = AmazonTest.class.getResource("/model/Model_A.graphml");
    URL url_B = AmazonTest.class.getResource("/model/Model_B.graphml");
    URL url_C = AmazonTest.class.getResource("/model/Model_C.graphml");
    URL url_D = AmazonTest.class.getResource("/model/Model_D.graphml");
    File file_A = new File(url_A.toURI());
    File file_B = new File(url_B.toURI());
    File file_C = new File(url_C.toURI());
    File file_D = new File(url_D.toURI());

    // Connect the model to a java class, and add it to graphwalker's modelhandler.
    // The model is to be executed using the following criteria:
    // EFSM: Extended finite state machine is set to true, which means we are using the data domain
    // in the model
    // Generator: a_star, we want to walk through the model using shortest possible path.
    // Stop condition: Edge coverage 100%, we want to walk every edge in the model.
    modelhandler.add("Model A", new Model_A(file_A, true, new RandomPathGenerator(new EdgeCoverage(1.0)), false));
    modelhandler.add("Model B", new Model_B(file_B, true, new RandomPathGenerator(new EdgeCoverage(1.0)), false));
    modelhandler.add("Model C", new Model_C(file_C, true, new RandomPathGenerator(new EdgeCoverage(1.0)), false));
    modelhandler.add("Model D", new Model_D(file_D, true, new RandomPathGenerator(new EdgeCoverage(1.0)), false));

    // Start executing the test
    modelhandler.execute("Model A");

    // Verify that the execution is complete, fulfilling the criteria from above.
    Assert.assertTrue(modelhandler.isAllModelsDone(), "Not all models are done");

    // Print the statistics from graphwalker
    String actualResult = modelhandler.getStatistics();
    System.out.println(actualResult);
  }
}
