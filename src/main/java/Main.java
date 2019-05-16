
public class Main {
	public static void main(String[] args) {

			String path = "C:\\Users\\Автоматика3\\IdeaProjects\\DZpasalgorithms\\Comtrades\\";
//			String path = "/Users/alexbol96/IdeaProjects/DZpasalgorithms/Comtrades/";
//			String path = "C:\\Users\\alexbol96\\IdeaProjects\\DZpasalgorithms\\Comtrades\\";
			String file = "KZ7";
			DataComtrade cd = new DataComtrade(path, file);

		Charts.createAnalogChart("3I0",0);
		Charts.addSeries("3I0",0,0);
		Charts.createAnalogChart("TRIP",1);
		Charts.addSeries("Trip",1,0);

		TZNPrele tznPrele = new TZNPrele();
		cd.setTznPrele(tznPrele);
		cd.run();
		}
}
