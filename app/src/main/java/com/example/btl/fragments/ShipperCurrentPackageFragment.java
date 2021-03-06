package com.example.btl.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.btl.R;
import com.example.btl.adapters.ActivePackageAdapter;
import com.example.btl.dao.CallbackTest;
import com.example.btl.dao.Package;
import com.example.btl.dao.PackageDAOImpl;

import java.util.ArrayList;
import java.util.List;

import static com.example.btl.MainActivity.MyPREFERENCES;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShipperCurrentPackageFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShipperCurrentPackageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShipperCurrentPackageFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    SharedPreferences sharedpreferences;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    List<Package> activePackageListData;
    private OnFragmentInteractionListener mListener;
    public ActivePackageAdapter activePackageAdapter;
    public ShipperCurrentPackageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShipperCurrentPackageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShipperCurrentPackageFragment newInstance(String param1, String param2) {
        ShipperCurrentPackageFragment fragment = new ShipperCurrentPackageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        sharedpreferences = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shipper_current_package, container, false);
        activePackageListData = new ArrayList<Package>();
        activePackageListData = getListData(this);
        final ListView activePackageList = view.findViewById(R.id.listActivePackage);
        activePackageAdapter = new ActivePackageAdapter(activePackageListData, getActivity());
        activePackageList.setAdapter(activePackageAdapter);
        return view;
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
    List<Package> getListData(ShipperCurrentPackageFragment pk){
        List<Package> list = new ArrayList<Package>();
        PackageDAOImpl packageDAO = new PackageDAOImpl();
        CallbackTest cb = new CallbackTest();
        list = packageDAO.getByIdShipper(pk, sharedpreferences.getInt("id", 1));
        System.out.println("return: " + list);
        return list;
    }
}
