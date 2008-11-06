package opennlp.model;

import java.io.File;
import java.io.IOException;

import opennlp.maxent.io.GISModelReader;
import opennlp.perceptron.PerceptronModelReader;

public class GenericModelReader extends AbstractModelReader {

  private AbstractModelReader delegateModelReader;
  
  public GenericModelReader (File f) throws IOException {
    super(f);
  }
  
  public void checkModelType() throws IOException {
    String modelType = readUTF();
    if (modelType.equals("Perceptron")) {
      delegateModelReader = new PerceptronModelReader(this.dataReader);
    }
    else if (modelType.equals("Maxent")) {
      delegateModelReader = new GISModelReader(this.dataReader);
    }
  }
  

  public AbstractModel constructModel() throws IOException {
    return delegateModelReader.constructModel();
  }
}
