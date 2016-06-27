import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import java.util.List;

public class SceneController
{    
    /* The stage that the scene belongs to, required to catch stage events and test for duplicate controllers. */
    private static Stage stage;     

    /* These FXML variables exactly corrispond to the controls that make up the scene, as designed in Scene 
     * Builder. It is important to ensure that these match perfectly or the controls won't be interactive. */
    @FXML   private Pane backgroundPane;    
    @FXML   private Label donutsLabel;    
    @FXML   private Button editButton;
    @FXML   private Button deleteButton;
    @FXML   private Button addButton;
    @FXML   private Button exitButton;
    @FXML   private ListView listView;

    public SceneController()          // The constructor method, called first when the scene is loaded.
    {
        System.out.println("Initialising controllers...");

        /* Our JavaFX application should only have one initial scene. The following checks to see
         * if a scene already exists (deterimed by if the stage variable has been set) and if so 
         * terminates the whole application with an error code (-1). */        
        if (stage != null)
        {
            System.out.println("Error, duplicate controller - terminating application!");
            System.exit(-1);
        }        

    } 

    @FXML   void initialize()           // The method automatically called by JavaFX after the constructor.
    {            
        /* The following assertions check to see if the JavaFX controls exists. If one of these fails, the
         * application won't work. If the control names in Scene Builder don't match the variables this fails. */ 
        System.out.println("Asserting controls...");
        try
        {
        	assert backgroundPane != null : "Can't find background pane.";
        	assert editButton != null : "Can't find edit button.";
        	assert deleteButton != null : "Can't find delete button.";
        	assert addButton != null : "Can't find add button.";
        	assert exitButton != null : "Can't find exit button.";
            assert listView != null : "Can't find list box.";
        }
        catch (AssertionError ae)
        {
            System.out.println("FXML assertion failure: " + ae.getMessage());
            Application.terminate();
        }

        /* Next, we load the list of fruit from the database and populate the listView. */
        System.out.println("Populating scene with items from the database...");        
        @SuppressWarnings("unchecked")
        List<Donut> targetList = listView.getItems();  // Grab a reference to the listView's current item list.
        Donut.readAll(targetList);                     // Hand over control to the fruit model to populate this list.
    }

    /* In order to catch stage events (the main example being the close (X) button being clicked) we need
     * to setup event handlers. This happens after the constructor and the initialize methods. Once this is
     * complete, the scene is fully loaded and ready to use. */
    public void prepareStageEvents(Stage stage)
    {
        System.out.println("Preparing stage events...");

        this.stage = stage;

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    System.out.println("Close button was clicked!");
                    Application.terminate();
                }
            });
    }       

    /* The next three methods are event handlers for clicking on the buttons. 
     * The names of these methods are set in Scene Builder so they work automatically. */    
    @FXML   void editClicked()
    {
        System.out.println("Edit was clicked!");        
    }

    @FXML   void addClicked()
    {
        System.out.println("Add was clicked!");
    }

    @FXML   void deleteClicked()
    {
        System.out.println("Delete was clicked!");
    }
    
    @FXML   void exitClicked()
    {
        System.out.println("Exit was clicked!");        
        Application.terminate();        // Call the terminate method in the main Application class.
    }

    /* This method, set in SceneBuilder to occur when the listView is clicked, establishes which
     * item in the view is currently selected (if any) and outputs it to the console. */    
    @FXML   void listViewClicked()
    {
        Donut selectedItem = (Donut) listView.getSelectionModel().getSelectedItem();

        if (selectedItem == null)
        {
            System.out.println("Nothing selected!");
        }
        else
        {
            System.out.println(selectedItem + " (id: " + selectedItem.DonutId + ") is selected.");
        }
    }    

}

