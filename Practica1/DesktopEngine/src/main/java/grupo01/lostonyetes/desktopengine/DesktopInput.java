package grupo01.lostonyetes.desktopengine;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import grupo01.lostonyetes.engine.IInput;
import grupo01.lostonyetes.engine.InputEvent;
import grupo01.lostonyetes.engine.InputType;

public class DesktopInput implements IInput, MouseListener {

    List<InputEvent> eventList;
    List<InputEvent> eventsToHandled;

    MouseListener mListener;

    DesktopEngine eng;

    public DesktopInput(JFrame view, DesktopEngine _eng){
        this.eventList = new ArrayList<>();
        this.eventsToHandled = new ArrayList<>();
        // Le dices a la ventana de escritorio: "Escucha los clics usando esta misma clase"
        view.addMouseListener(this);
        this.eng = _eng;
    }

    @Override
    synchronized public List<InputEvent> getEvents() {
        eventList.clear();

        for (InputEvent i : eventsToHandled)
        {
            eventList.add(i);
        }

        eventsToHandled.clear();

        return eventList;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        int x = eng.getGraphics().getLogicX(mouseEvent.getX());

        int y = eng.getGraphics().getLogicY(mouseEvent.getY());
        InputEvent ev = new InputEvent(x,y, InputType.ONTOUCHDOWN,0);

        synchronized (this) {
            eventsToHandled.add(ev);
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

        int x = eng.getGraphics().getLogicX(mouseEvent.getX());

        int y = eng.getGraphics().getLogicY(mouseEvent.getY());
        InputEvent ev = new InputEvent(x,y, InputType.ONPRESSED,0);

        synchronized (this) {
            eventsToHandled.add(ev);
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

        int x = eng.getGraphics().getLogicX(mouseEvent.getX());

        int y = eng.getGraphics().getLogicY(mouseEvent.getY());
        InputEvent ev = new InputEvent(x,y, InputType.ONRELEASE,0);

        synchronized (this) {
            eventsToHandled.add(ev);
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
