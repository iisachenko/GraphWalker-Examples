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
    URL url_1 = AmazonTest.class.getResource("/model/Model_1.graphml");
    URL url_2 = AmazonTest.class.getResource("/model/Model_2.graphml");
    URL url_3 = AmazonTest.class.getResource("/model/Model_3.graphml");
    URL url_4 = AmazonTest.class.getResource("/model/Model_4.graphml");
    File file_1 = new File(url_1.toURI());
    File file_2 = new File(url_2.toURI());
    File file_3 = new File(url_3.toURI());
    File file_4 = new File(url_4.toURI());

    // Connect the model to a java class, and add it to graphwalker's modelhandler.
    // The model is to be executed using the following criteria:
    // EFSM: Extended finite state machine is set to true, which means we are using the data domain
    // in the model
    // Generator: a_star, we want to walk through the model using shortest possible path.
    // Stop condition: Edge coverage 100%, we want to walk every edge in the model.
    modelhandler.add("Model 1", new Model_A(file_1, true, new RandomPathGenerator(new EdgeCoverage(1.0)), false));
    modelhandler.add("Model 2", new Model_B(file_2, true, new RandomPathGenerator(new EdgeCoverage(1.0)), false));
    modelhandler.add("Model 3", new Model_C(file_3, true, new RandomPathGenerator(new EdgeCoverage(1.0)), false));
    modelhandler.add("Model 4", new Model_D(file_4, true, new RandomPathGenerator(new EdgeCoverage(1.0)), false));

    // Start executing the test
    modelhandler.execute("Model 1");

    // Verify that the execution is complete, fulfilling the criteria from above.
    Assert.assertTrue(modelhandler.isAllModelsDone(), "Not all models are done");

    // Print the statistics from graphwalker
    String actualResult = modelhandler.getStatistics();
    System.out.println(actualResult);
  }
}
