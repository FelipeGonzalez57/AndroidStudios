package mx.edu.utng.citews;

/**
 * Created by PandithaGD on 27/04/2017.
 */


import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;

public class Cite implements KvmSerializable{
    private  int id;
    private String usuario;
    private String room;
    private String recurrenceParent;
    private String subject;
    private String inicio;
    private String finall;
    private String recurrenceRule;
    private String annotations;
    private String descripcion;
    private String reminder;


    public Cite(int id, String usuario, String room, String inicio, String subject, String recurrenceParent, String finall, String recurrenceRule, String annotations, String descripcion, String reminder) {
        this.id = id;
        this.usuario = usuario;
        this.room = room;
        this.inicio = inicio;
        this.subject = subject;
        this.recurrenceParent = recurrenceParent;
        this.finall = finall;
        this.recurrenceRule = recurrenceRule;
        this.annotations = annotations;
        this.descripcion = descripcion;
        this.reminder = reminder;
    }

    public Cite() {
        this(0,"","","","","","","","","","");
    }


    @Override
    public Object getProperty(int i) {
        switch (i){
            case 0:
                return  id;
            case 1:
                return  usuario;
            case 2:
                return  room;
            case 3:
                return  recurrenceParent;
            case 4:
                return  subject;
            case 5:
                return  inicio;
            case 6:
                return  finall;
            case 7:
                return  recurrenceRule;
            case 8:
                return  annotations;
            case 9:
                return  descripcion;
            case 10:
                return  reminder;
        }
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 11;
    }

    @Override
    public void setProperty(int i, Object o) {
        switch (i){
            case 0:
                id=Integer.parseInt(o.toString());
                break;
            case 1:
                usuario=o.toString();
                break;
            case 2:
                room=o.toString();
                break;
            case 3:
                recurrenceParent=o.toString();
                break;
            case 4:
                subject=o.toString();
                break;
            case 5:
                inicio=o.toString();
                break;
            case 6:
                finall=o.toString();
                break;
            case 7:
                recurrenceRule=o.toString();
                break;
            case 8:
                annotations=o.toString();
                break;
            case 9:
                descripcion=o.toString();
                break;
            case 10:
                reminder=o.toString();
                break;
            default:
                break;
        }

    }

    @Override
    public void getPropertyInfo(int i, Hashtable hashtable, PropertyInfo propertyInfo) {
        switch (i){
            case 0:
                propertyInfo.type=PropertyInfo.INTEGER_CLASS;
                propertyInfo.name="id";
                break;
            case 1:
                propertyInfo.type=PropertyInfo.INTEGER_CLASS;
                propertyInfo.name="usuario";
                break;
            case 2:
                propertyInfo.type=PropertyInfo.INTEGER_CLASS;
                propertyInfo.name="room";
                break;

            case 3:
                propertyInfo.type=PropertyInfo.INTEGER_CLASS;
                propertyInfo.name="recurrenceParent";
                break;
            case 4:
                propertyInfo.type=PropertyInfo.STRING_CLASS;
                propertyInfo.name="subject";
                break;
            case 5:
                propertyInfo.type=PropertyInfo.STRING_CLASS;
                propertyInfo.name="inicio";
                break;
            case 6:
                propertyInfo.type=PropertyInfo.STRING_CLASS;
                propertyInfo.name="finall";
                break;
            case 7:
                propertyInfo.type=PropertyInfo.STRING_CLASS;
                propertyInfo.name="recurrenceRule";
                break;
            case 8:
                propertyInfo.type=PropertyInfo.STRING_CLASS;
                propertyInfo.name="annotations";
                break;
            case 9:
                propertyInfo.type=PropertyInfo.STRING_CLASS;
                propertyInfo.name="descripcion";
                break;
            case 10:
                propertyInfo.type=PropertyInfo.STRING_CLASS;
                propertyInfo.name="reminder";
                break;

            default:
                break;
        }

    }

}
