package com.example.btl.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.btl.R;
import com.example.btl.dao.Package;
import com.example.btl.dao.PackageDAOImpl;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PackageFilterFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PackageFilterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PackageFilterFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private GoogleMap map;
    private SupportMapFragment mapFragment;
    private LatLng lng;
    private String location;
    private Button findBtn;
    PopupWindow packagePopupWindow;
    private OnFragmentInteractionListener mListener;
    public List<Package> packageList;
    public PackageFilterFragment() {
        PackageDAOImpl packageDAO = new PackageDAOImpl();
        packageList = new ArrayList<>();
        packageList = packageDAO.getAll(this);
        try {
            Thread.currentThread().join(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static PackageFilterFragment newInstance(String param1, String param2) {
        PackageFilterFragment fragment = new PackageFilterFragment();
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
        if (!Places.isInitialized()) {
            Places.initialize(getActivity(), "AIzaSyA2d8RYsPfPLKspnYnQciZDFBJGbFyNDUc");
        }
        LocationServices.getFusedLocationProviderClient(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_package_filter, container, false);
        mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map1);

        findBtn = view.findViewById(R.id.btn_find);
        mapFragment.getMapAsync(this);
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        map.setMyLocationEnabled(true);
        map.setOnMyLocationClickListener(new GoogleMap.OnMyLocationClickListener() {
            @Override
            public void onMyLocationClick(@NonNull Location location) {
                Geocoder geocoder;
                List<Address> addresses;
                geocoder = new Geocoder(getContext(), Locale.getDefault());
                try {
                    addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                    String address = addresses.get(0).getAddressLine(0);
                    Toast.makeText(getActivity(), "Current location:\n" + address , Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getChildFragmentManager().findFragmentById(R.id.place_autocomplete_fragment_filter);
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                location = place.getName();
                Geocoder gc = new Geocoder(getContext(), Locale.getDefault());
                try {
                    List<Address> addresses = gc.getFromLocationName(location, 5);
                    lng = new LatLng(addresses.get(0).getLatitude(), addresses.get(0).getLongitude());
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(lng, 16f));
                    map.addMarker(new MarkerOptions()
                            .position(lng)
                            .title("")
                            .snippet("addresses.get(0).getFeatureName()")
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                    );
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onError(@NonNull Status status) { }
        });
        findBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for(Package pk : packageList){
                            Geocoder gc = new Geocoder(getContext(), Locale.getDefault());
                            System.out.println("++++++++++++" + pk.getSendAddress());
                            try {
                                List<Address> addresses = gc.getFromLocationName(pk.getRecieveAddress(), 5);
                                System.out.println(addresses.get(0) + "-----------------------");
                                lng = new LatLng(addresses.get(0).getLatitude(), addresses.get(0).getLongitude());
                                map.addMarker(new MarkerOptions()
                                        .position(lng)
                                        .title("")
                                        .snippet("addresses.get(0).getFeatureName()")
                                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                                );
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }
        );
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

    @Override
    public boolean onMarkerClick(Marker marker) {
        System.out.println("Click");

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE);

        // Inflate the custom layout/view
//        View customView = inflater.inflate(R.layout.get_package_popup,null);
//        TextView sendAddress = customView.findViewById(R.id.sendAddress);
//        TextView recieveAddress = customView.findViewById(R.id.recieveAddress);
//        TextView advanceMoney = customView.findViewById(R.id.advanceMoney);
//        TextView shipCost = customView.findViewById(R.id.shipCost);
//        sendAddress.setText("Từ: " + item.getSendAddress());
//        recieveAddress.setText("Đến: " + item.getRecieveAddress());
//        advanceMoney.setText("Ứng: " + item.getAdvanceMoney());
//        shipCost.setText("Phí ship: " + item.getShipCost());
//        // Initialize a new instance of popup window
//        packagePopupWindow = new PopupWindow(
//                customView,
//                700,
//                800
//        );
//
//        // Set an elevation value for popup window
//        // Call requires API level 21
//        if(Build.VERSION.SDK_INT>=21){
//            packagePopupWindow.setElevation(5.0f);
//        }
//
//        // Get a reference for the custom view close button
//        Button recievePackageButton = (Button) customView.findViewById(R.id.recievePackageButton);
//        Button cancelRecieveButton = (Button) customView.findViewById(R.id.cancelRecieveButton);
//        //  a click listener for the popup window close button
//        cancelRecieveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Dismiss the popup window
//                packagePopupWindow.dismiss();
//            }
//        });
//        recievePackageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println("Want to recieve");
//                ShipDAOImpl shipDAO = new ShipDAOImpl();
//                shipDAO.recieve(item.getId(), 1);
//                Intent intent = new Intent(getContext(), ShipperNavigatorMenu.class);
//                startActivity(intent);
//            }
//        });
//
//        // Finally, show the popup window at the center location of root relative layout
//        packagePopupWindow.showAtLocation(getActivity().findViewById(R.id.popupLayout), Gravity.CENTER,0,0);
//        return true;
////                System.out.println(pk.getRecieveAddress());

        return false;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
