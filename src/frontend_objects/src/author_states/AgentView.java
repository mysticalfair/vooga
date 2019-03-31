package author_states;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class AgentView extends DraggableView {

    /**
     *
     * @author Mary Stuart Elder
     */

    private ImageView myView;

    public AgentView(String imageName){
        var localImage = new Image(imageName);
        myView = getFormattedView(localImage);
    }

    public ImageView getView(){
        return myView;
    }

    private ImageView getFormattedView(Image image){
        var view = new ImageView(image);
        view.setId("img");
        view.setFitWidth(100);
        view.setFitHeight(100);

        setMouseActions(view);

        return view;
    }

    @Override
    public void mouseReleased(MouseEvent event){
        if( event.getSceneX() > 50 ){
            ((ImageView)(event.getSource())).setTranslateX(getStartX());
            ((ImageView)(event.getSource())).setTranslateY(getStartY());
        }
    }
}
