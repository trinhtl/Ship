package com.example.btl.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.btl.ListPackageAdapter;
import com.example.btl.R;
import com.example.btl.dao.CallbackTest;
import com.example.btl.dao.Item;
import com.example.btl.dao.ItemDAOImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.socket.client.Socket;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PackageListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PackageListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PackageListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    List<Item> pakageListData;
    Socket socket;
    public ListPackageAdapter listPackageAdapter;
    private OnFragmentInteractionListener mListener;

    public PackageListFragment() {
        System.out.println("Public constructor");
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PackageListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PackageListFragment newInstance(String param1, String param2) {
        System.out.println("New instance");
        PackageListFragment fragment = new PackageListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        System.out.println("onCreate");
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        System.out.println("onCreateView");
        final View view = inflater.inflate(R.layout.fragment_package_list, container, false);

        this.pakageListData = new ArrayList<>();
//        pakageListData = getListData2();
        pakageListData = getListData(this);
        final ListView pakageList = (ListView) view.findViewById(R.id.packageList);
        listPackageAdapter = new ListPackageAdapter(pakageListData, getActivity());
        pakageList.setAdapter(listPackageAdapter);
        pakageList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("Click");
                Item item = (Item)pakageList.getItemAtPosition(position);
                System.out.println(item.toString());
            }
        });
        return view;
    }


    public List<Item> callbackView(List<Item> itemList){
        System.out.println("test cb...." + itemList);
        return itemList;
    }

    private List<Item> getListData(PackageListFragment pk){
        Log.i("getList", "get data");
        List<Item> list = new ArrayList<Item>();
        ItemDAOImpl itemDAO = new ItemDAOImpl();
        CallbackTest cb = new CallbackTest();
        list = itemDAO.getAll(pk);
        System.out.println("return: " + list);
        return list;
    }
    private List<Item> getListData2(){
        Log.i("getList", "get data");
        List<Item> list = new ArrayList<Item>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        Item first = new Item(1, "a", "Jack", "50k", "20k", String.valueOf(new Date(dateFormat.format(date))), "181 Xuân Thủy, Cầu Giấy", "18 Ba Đình", "Cần gấp");
        list.add(first);
        Item second = new Item(2, null, "Mary", "50k", "20k", String.valueOf(new Date(dateFormat.format(date))), "181 Xuân Thủy, Cầu Giấy", "18 Ba Đình", null);
        list.add(second);
        return  list;
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        System.out.println("onAttach");
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        System.out.println("onDetach");
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    private class PackageListFragmentAsync extends AsyncTask{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            ItemDAOImpl itemDAO = new ItemDAOImpl();
            //pakageListData = itemDAO.getAll();
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
        }
    }
    public interface ChangeTest{

    }
}
