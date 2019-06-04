package com.example.btl.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.btl.adapters.ListPackageAdapter;
import com.example.btl.R;
import com.example.btl.ShipperNavigatorMenu;
import com.example.btl.dao.CallbackTest;
import com.example.btl.dao.Item;
import com.example.btl.dao.ItemDAOImpl;
import com.example.btl.dao.ShipDAOImpl;

import java.util.ArrayList;
import java.util.List;

import io.socket.client.Socket;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

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
    PopupWindow packagePopupWindow;
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
            public void onItemClick(AdapterView<?> parent, View view, int position, final long id) {

                System.out.println("Click");
                final Item item = (Item)pakageList.getItemAtPosition(position);
                System.out.println(item.toString());
                LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(LAYOUT_INFLATER_SERVICE);

                // Inflate the custom layout/view
                View customView = inflater.inflate(R.layout.get_package_popup,null);
                TextView sendAddress = customView.findViewById(R.id.sendAddress);
                TextView recieveAddress = customView.findViewById(R.id.recieveAddress);
                TextView advanceMoney = customView.findViewById(R.id.advanceMoney);
                TextView shipCost = customView.findViewById(R.id.shipCost);
                sendAddress.setText("Từ: " + item.getSendAddress());
                recieveAddress.setText("Đến: " + item.getRecieveAddress());
                advanceMoney.setText("Ứng: " + item.getAdvanceMoney());
                shipCost.setText("Phí ship: " + item.getShipCost());
                // Initialize a new instance of popup window
                packagePopupWindow = new PopupWindow(
                        customView,
                        700,
                        800
                );

                // Set an elevation value for popup window
                // Call requires API level 21
                if(Build.VERSION.SDK_INT>=21){
                    packagePopupWindow.setElevation(5.0f);
                }

                // Get a reference for the custom view close button
                Button recievePackageButton = (Button) customView.findViewById(R.id.recievePackageButton);
                Button cancelRecieveButton = (Button) customView.findViewById(R.id.cancelRecieveButton);
                //  a click listener for the popup window close button
                cancelRecieveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Dismiss the popup window
                        packagePopupWindow.dismiss();
                    }
                });
                recievePackageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println("Want to recieve");
                        ShipDAOImpl shipDAO = new ShipDAOImpl();
                        shipDAO.recieve(item.getId(), 1);
                        Intent intent = new Intent(getContext(), ShipperNavigatorMenu.class);
                        startActivity(intent);
                    }
                });

                // Finally, show the popup window at the center location of root relative layout
                packagePopupWindow.showAtLocation(getActivity().findViewById(R.id.popupLayout), Gravity.CENTER,0,0);
            }
        });
        return view;
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
