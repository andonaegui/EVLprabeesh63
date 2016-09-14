package com.example.randona.myapplication;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Wolf on 14-Sep-16.
 */
public class MyAdapter extends BaseExpandableListAdapter {
    //Esta lista es para representar los head lines
    private List<String> header_titles;
    //Es una coleccion de objetos, pero desordenados, como un array d
    private HashMap<String, List<String>> child_titles;
    private Context ctx;
    //Para iniciar estas variables necesitamos un constructor
    MyAdapter(Context ctx, List<String> header_titles, HashMap<String, List<String>> child_titles)
    {
        this.ctx = ctx;
        this.child_titles = child_titles;
        this.header_titles = header_titles;
    }

    @Override
    public int getGroupCount() {
        //Devolvemos X elementos disponibles en el groupcount (en este caso parent)
        return header_titles.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        //Devolvemos el numero de child items disponibles en cada heading (3 tipos de pelis)
        return child_titles.get(header_titles.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        //Obtener un objeto disponible en el grupo
        return header_titles.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        //Devolver el Child item disponible en un heading particular
        return child_titles.get(header_titles.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String title = (String) this.getGroup(groupPosition);
        //Concepto: Inflate = inflar los layouts en tiempo de ejecución
        //Revisamos si el convertView si existe y lo inflamos, si no lo creamos
        if (convertView == null)
        {
            //Creamos un object layout inflator
            LayoutInflater layoutInflater = (LayoutInflater) this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //Inflamos el layout para el parent
            convertView = layoutInflater.inflate(R.layout.paent_layout,null);
        }
        //Si no existe inicializamos el textView
        TextView textView = (TextView) convertView.findViewById(R.id.heading_item);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setText(title);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        // Requerimos el title para el child y lo obtenemos del metodo de arriba getChild
        String title = (String) this.getChild(groupPosition, childPosition);
        //Chequeamos si el layout ya esta inflado o no.
        if (convertView == null)
        {
            //Lo mismo que el anterior, solo cambia el recurso layout
            LayoutInflater layoutInflater = (LayoutInflater) this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.child_layout,null);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.child_item);
        textView.setText(title);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
