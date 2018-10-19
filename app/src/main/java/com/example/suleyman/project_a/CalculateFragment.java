package com.example.suleyman.project_a;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.suleyman.project_a.Adapter.CustomAdapter;
import com.example.suleyman.project_a.Common.Menular;
import com.example.suleyman.project_a.Model.Expenses;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

import org.apache.commons.io.FileUtils;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Art Servis on 11/22/2017.
 */

public class CalculateFragment extends Fragment {


    RelativeLayout searchMoreLyt, calculateParentLyt, transportLyt, transportTypeLyt, inputNodesLyt, outputNodesLyt,
            wagonTypeLyt, regionLyt, weightLyt, calculateLyt, dateLyt;
    LinearLayout hiddenSearchMoreLyt, allRightsLyt;
    TextView search_more_txt, integratorTxt, selectedTransportTypeTxt, selectedRejimTxt, selectedInputNodeTxt,
            selectedOutputNodeTxt, selectedWagonTypeTxt, selectedWeightTxt, selectedRegionTxt, dateTxt;
    ImageView beginningStationImg, destinationSearchImg, qnqsearchImg, etsnqSearchImg;
    EditText beginningStationEdt, destinationEdt, qnqEdt, etsnqEdt;
    Boolean open = false;
    DatePickerDialog.OnDateSetListener dateSetListener;
    String user_="";
    String pass_="";
    String name_="";
    String userId_ = "";
    String url =  "https://ady.express/Plan.asmx/Search";
    final ArrayList<String> transportTypes = new ArrayList<String>();
    final ArrayList<String> wagonTypes = new ArrayList<String>();
    final ArrayList<String> rejimTypes = new ArrayList<String>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_search, container, false);

        searchMoreLyt = (RelativeLayout) view.findViewById(R.id.search_more_lyt);
        hiddenSearchMoreLyt = (LinearLayout) view.findViewById(R.id.hidden_search_more_lyt);
        search_more_txt = (TextView) view.findViewById(R.id.search_more_txt);
        calculateParentLyt = (RelativeLayout) view.findViewById(R.id.calculate_parent_lyt);
        transportLyt = (RelativeLayout) view.findViewById(R.id.transportLyt);
        selectedTransportTypeTxt = (TextView) view.findViewById(R.id.selectedTransportTypeTxt);
        transportTypeLyt = (RelativeLayout) view.findViewById(R.id.transportTypeLyt);
        inputNodesLyt = (RelativeLayout) view.findViewById(R.id.inputNodesLyt);
        outputNodesLyt = (RelativeLayout) view.findViewById(R.id.outputNodesLyt);
        selectedRejimTxt = (TextView) view.findViewById(R.id.selectedRejimTxt);
        selectedInputNodeTxt = (TextView) view.findViewById(R.id.selectedInputNodeTxt);
        selectedOutputNodeTxt = (TextView) view.findViewById(R.id.selectedOutputNodeTxt);
        beginningStationEdt= (EditText) view.findViewById(R.id.beginningStationEdt);
        beginningStationImg = (ImageView) view.findViewById(R.id.beginningSearchImg);
        destinationEdt = (EditText) view.findViewById(R.id.destinationEdt);
        qnqEdt = (EditText) view.findViewById(R.id.qnqEdt);
        destinationSearchImg = (ImageView) view.findViewById(R.id.destinationSearchImg);
        qnqsearchImg = (ImageView) view.findViewById(R.id.qnqSearchImg);
        etsnqEdt = (EditText) view.findViewById(R.id.etsnqEdt);
        etsnqSearchImg = (ImageView) view.findViewById(R.id.etsnqSearchImg);
        wagonTypeLyt = (RelativeLayout) view.findViewById(R.id.wagonTypeLyt);
        selectedWagonTypeTxt = (TextView) view.findViewById(R.id.selectedWagonTypeTxt);
        weightLyt = (RelativeLayout) view.findViewById(R.id.weightLyt);
        selectedWeightTxt = (TextView) view.findViewById(R.id.selectedWeightTxt);
        regionLyt = (RelativeLayout) view.findViewById(R.id.regionLyt);
        selectedRegionTxt = (TextView) view.findViewById(R.id.selectedRegionTxt);
        calculateLyt = (RelativeLayout) view.findViewById(R.id.calculateLyt);
        dateLyt = (RelativeLayout) view.findViewById(R.id.dateLyt);
        dateTxt = (TextView) view.findViewById(R.id.dateTxt);

        user_ = getArguments().getString("sess_user");
        pass_ =  getArguments().getString("sess_pass");
        name_ =  getArguments().getString("sess_name");
        userId_ = getArguments().getString("sess_id");

        JsonParse jsonParse = new JsonParse();
        jsonParse.execute();


        final Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = dFormat.format(calendar.getTime());
        dateTxt.setText(strDate);

        dateLyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);


                dateSetListener = new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        int m = month + 1;
                        String time = dayOfMonth + "." + m + "." + year;
                        dateTxt.setText(time);
                    }
                };

                DatePickerDialog dialog = new DatePickerDialog(
                        getActivity(),
                        R.style.datePickerStyle,
                        dateSetListener, year, month, day);
                dialog.getWindow();
                dialog.show();
                dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            }
        });


        searchMoreLyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hiddenSearchMoreLyt.getVisibility() == View.GONE)
                {
                    hiddenSearchMoreLyt.setVisibility(View.VISIBLE);
                    search_more_txt.setText("ƏSAS AXTARIŞ");
                    changePlaceOfBUtton(73);
                }

                else
                {
                    hiddenSearchMoreLyt.setVisibility(View.GONE);
                    search_more_txt.setText("ƏTRAFLI AXTARIŞ");
                    if(open == false) {
                        changePlaceOfBUtton(150);
                    }
                }
            }
        });

        beginningStationImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ArrayList<String> beginningStations = new ArrayList<String>();
                beginningStations.add("12345");
                beginningStations.add("2345");
                beginningStations.add("2335");
                beginningStations.add("22455");
                beginningStations.add("2355");

                searchDialog(beginningStationEdt, beginningStations);

            }
        });

        destinationSearchImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ArrayList<String> destionationStations = new ArrayList<String>();
                destionationStations.add("12345");
                destionationStations.add("2345");
                destionationStations.add("2335");
                destionationStations.add("22455");
                destionationStations.add("2355");

                searchDialog(destinationEdt, destionationStations);
            }
        });

        qnqsearchImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ArrayList<String> qnqCodes = new ArrayList<String>();
                qnqCodes.add("12345");
                qnqCodes.add("2345");
                qnqCodes.add("2335");
                qnqCodes.add("22455");
                qnqCodes.add("2355");

                searchDialog(qnqEdt, qnqCodes);
            }
        });

        etsnqSearchImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ArrayList<String> etsnqCodes = new ArrayList<String>();
                etsnqCodes.add("12345");
                etsnqCodes.add("2345");
                etsnqCodes.add("2335");
                etsnqCodes.add("22455");
                etsnqCodes.add("2355");
                etsnqCodes.add("12345");
                etsnqCodes.add("2345");
                etsnqCodes.add("2335");
                etsnqCodes.add("22455");
                etsnqCodes.add("2355");
                etsnqCodes.add("12345");
                etsnqCodes.add("2345");
                etsnqCodes.add("2335");
                etsnqCodes.add("22455");
                etsnqCodes.add("2355");

                searchDialog(etsnqEdt, etsnqCodes);
            }
        });

        transportLyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectionDialog(transportTypes, selectedTransportTypeTxt);
            }
        });

        inputNodesLyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ArrayList<String> inputNodes = new ArrayList<String>();
                inputNodes.add("ƏLƏT");
                inputNodes.add("YALAMA");
                inputNodes.add("BÖYÜK KƏSİK");
                selectionDialog(inputNodes, selectedInputNodeTxt);

            }
        });

        transportTypeLyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectionDialog(rejimTypes, selectedRejimTxt);

            }
        });

        outputNodesLyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ArrayList<String> outputNodes = new ArrayList<String>();
                outputNodes.add("ƏLƏT");
                outputNodes.add("YALAMA");
                outputNodes.add("BÖYÜK KƏSİK");
                outputNodes.add("ƏLƏT");
                outputNodes.add("YALAMA");
                outputNodes.add("BÖYÜK KƏSİK");
                outputNodes.add("ƏLƏT");
                outputNodes.add("YALAMA");
                outputNodes.add("BÖYÜK KƏSİK");
                outputNodes.add("ƏLƏT");
                selectionDialog(outputNodes, selectedOutputNodeTxt);
            }
        });

        wagonTypeLyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                selectionDialog(wagonTypes, selectedWagonTypeTxt);

            }
        });


        weightLyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ArrayList<String> weights = new ArrayList<String>();
                weights.add("20 FUT");
                weights.add("40 FUT");
                selectionDialog(weights, selectedWeightTxt);
            }
        });


        regionLyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ArrayList<String> regions = new ArrayList<String>();
                regions.add("AZƏRBAYCAN");
                regions.add("BEYNƏLXALQ");
                selectionDialog(regions, selectedRegionTxt);

            }
        });

        calculateLyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DisplayMetrics metrics = getResources().getDisplayMetrics();
                int width = metrics.widthPixels;
                int height = metrics.heightPixels;

                final Dialog dialog =  new Dialog(getActivity());
                View child = getActivity().getLayoutInflater().inflate(R.layout.new_result_dialog, null);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(child);
                // dialog.getWindow().setLayout(width, height);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.show();

                final ListView resultListview = (ListView) dialog.findViewById(R.id.resultListView);
                final RelativeLayout doluLyt = (RelativeLayout) dialog.findViewById(R.id.doluLyt);
                final RelativeLayout bosLyt = (RelativeLayout) dialog.findViewById(R.id.bosLyt);
                final RelativeLayout totalLyt = (RelativeLayout) dialog.findViewById(R.id.totalLyt);
                final TextView totalTxt = (TextView) dialog.findViewById(R.id.totalTxt);
                final TextView doluTxt = (TextView) dialog.findViewById(R.id.doluTxt);
                final TextView bosTxt = (TextView) dialog.findViewById(R.id.bosTxt);
                final RelativeLayout umumiLyt = (RelativeLayout) dialog.findViewById(R.id.umumiLyt);
                final RelativeLayout yekunLyt = (RelativeLayout) dialog.findViewById(R.id.yekunLyt);
                ImageView closeImg = (ImageView) dialog.findViewById(R.id.closeImg);
                RelativeLayout emrlerLyt = (RelativeLayout) dialog.findViewById(R.id.emrlerLyt);
                RelativeLayout sendLyt = (RelativeLayout) dialog.findViewById(R.id.sendBtn);
                final TextView umumiAlisCurrentMpsTxt = (TextView) dialog.findViewById(R.id.umumiAlisCurrentMpsTxt);
                final TextView umumiAlisCurrentSpsTxt = (TextView) dialog.findViewById(R.id.umumiAlisCurrentSpsTxt);
                final TextView umumiSatisCurrentMpsTxt = (TextView) dialog.findViewById(R.id.umumiSatisCurrentMpsTxt);
                final TextView umumiSatisCurrentSpsTxt = (TextView) dialog.findViewById(R.id.umumiSatisCurrentSpsTxt);
                final TextView yekunAlisCurrentMpsTxt = (TextView) dialog.findViewById(R.id.yekunAlisCurrentMpsTxt);
                final TextView yekunAlisCurrentSpsTxt = (TextView) dialog.findViewById(R.id.yekunAlisCurrentSpsTxt);
                final TextView yekunSatisCurrentMpsTxt = (TextView) dialog.findViewById(R.id.yekunSatisCurrentMpsTxt);
                final TextView yekunSatisCurrentSpsTxt = (TextView) dialog.findViewById(R.id.yekunSatisCurrentSpsTxt);
                final TextView transportTypeTxt = (TextView) dialog.findViewById(R.id.tranportTypeTxt);
                final TextView firstStation = (TextView) dialog.findViewById(R.id.firsStation);
                final TextView secondSattion = (TextView) dialog.findViewById(R.id.secondStation);
                final TextView currentDistance = (TextView) dialog.findViewById(R.id.currentDistanceTxt);
                final TextView currentQnq = (TextView) dialog.findViewById(R.id.currentQnqTxt);


                Expenses adyDasima = new Expenses("Ady Dasima", "400", "500", "280", "320");
                Expenses expense1 = new Expenses("Kaspar", "200", "250");
                Expenses expense2 = new Expenses("Nakad", "150", "250");
                Expenses expense3 = new Expenses("Vikad", "200", "400");

                final ArrayList<Expenses> arrayList = new ArrayList<>();
                arrayList.add(adyDasima);
                arrayList.add(expense1);
                arrayList.add(expense2);
                arrayList.add(expense3);
                arrayList.add(expense3);
                arrayList.add(expense2);
                arrayList.add(expense1);
                arrayList.add(expense3);
                arrayList.add(expense2);
                arrayList.add(expense1);
                arrayList.add(expense1);
                arrayList.add(expense1);
                arrayList.add(expense2);
                arrayList.add(expense3);
                arrayList.add(expense1);


                emrlerLyt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ArrayList<String> emrlerList = new ArrayList<String>();
                        emrlerList.add("kjsdfbsdljfsbkdfsbjkdfjsblkdfjsblkdfjsbdklfjsbdkfjsbdklfbsdlfk");
                        emrlerList.add("kjsdfbsdljfsbkdfsbjkdfjsblkdfjsbfbwskl;fbs;dbjs;dkjfbsdkjf;fjkwbkefjbwfelkdfjsbdklfjsbdkfjsbdklfbsdlfk");
                        emrlerList.add("kjsdfbkdfjsbdklfjsbdkfjsbdklfbsdlfk");
                        emrlerList.add("kjsdfbsdljfsbkdfsbjkdfjsblkdfjsblkdfjsbdklfjsbdkfjsbdklfbsdlfk");
                        emrlerList.add("kjsdfbsdljfsklfbsdlfk");
                        emrlerList.add("kjsdfbsdljfsbkdfsbjkdfjsblkdfjsblkdfjsbdklfjsbdkfjsbdklfbsdlfk");
                        emrlerList.add("kjsdfbsdljfsbkdfsbjkdfjsblkdfjsblkdfjsbdklfjsbdkfjsbdklfbsdlfk");

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, emrlerList);


                        final Dialog dialog =  new Dialog(getActivity());
                        View child = getActivity().getLayoutInflater().inflate(R.layout.new_emrler_popup, null);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(child);
                        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                        dialog.show();

                        ListView listView = (ListView) dialog.findViewById(R.id.listView);
                        listView.setAdapter(adapter);

                        ImageView closeImg = (ImageView) dialog.findViewById(R.id.closeBtn);

                        closeImg.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                    }
                });


                closeImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                doluLyt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        changeButtonColor(doluLyt, bosLyt, totalLyt, doluTxt, bosTxt, totalTxt);
                        resultListview.setVisibility(View.VISIBLE);
                        umumiLyt.setVisibility(View.VISIBLE);
                        yekunLyt.setVisibility(View.GONE);
                    }
                });

                bosLyt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        changeButtonColor(bosLyt, doluLyt, totalLyt, bosTxt, doluTxt, totalTxt);
                        resultListview.setVisibility(View.VISIBLE);
                        umumiLyt.setVisibility(View.VISIBLE);
                        yekunLyt.setVisibility(View.GONE);
                    }
                });

                totalLyt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        changeButtonColor(totalLyt, doluLyt, bosLyt, totalTxt, doluTxt, bosTxt);
                        resultListview.setVisibility(View.GONE);
                        umumiLyt.setVisibility(View.GONE);
                        yekunLyt.setVisibility(View.VISIBLE);
                    }
                });

                CustomAdapter adapter = new CustomAdapter(getActivity(), arrayList);
                resultListview.setAdapter(adapter);


                sendLyt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        final Dialog dialog =  new Dialog(getActivity());
                        View child = getActivity().getLayoutInflater().inflate(R.layout.custom_email_dialog, null);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(child);
                        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                        dialog.show();

                        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

                        final EditText emailEdt = (EditText) dialog.findViewById(R.id.emailEdt);
                        final RelativeLayout gonderLyt = (RelativeLayout) dialog.findViewById(R.id.gonderBtn);
                        ImageView closeImg = (ImageView) dialog.findViewById(R.id.closeImg);
                        final ProgressBar progressBar = (ProgressBar) dialog.findViewById(R.id.progressBar);
                        final ImageView doneImd = (ImageView) dialog.findViewById(R.id.doneImg);
                        final TextView sendingInformationTxt = (TextView) dialog.findViewById(R.id.sendingInformationTxt);
                        final RelativeLayout progressLyt  = (RelativeLayout) dialog.findViewById(R.id.progressLyt);
                        final RelativeLayout okLyt = (RelativeLayout) dialog.findViewById(R.id.okBtn);

                        closeImg.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });



                        final File pdfFolder = new File(Environment.getExternalStorageDirectory(), "ResultsPdf");
                        if (!pdfFolder.exists()) {
                            pdfFolder.mkdir();
                        }

                        final File myFile;
                        Date date = new Date();
                        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(date);

                        myFile = new File("/" + pdfFolder + "/" + timeStamp + ".pdf");

                        gonderLyt.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {


                                OutputStream outputStream = null;
                                try {
                                    outputStream = new FileOutputStream(myFile);
                                    Document document = new Document();
                                    PdfWriter.getInstance(document, outputStream);

                                    BaseFont bf = BaseFont.createFont("assets/fonts/AZER_TM.ttf", "Cp1254", BaseFont.EMBEDDED);
                                    Font font1 = new Font(bf, 22);
                                    Font font2 = new Font(bf, 16);

                                    PdfPTable table = new PdfPTable(3);

                                    PdfPCell cell;
                                    PdfPTable inner;

                                    table.setSpacingAfter(30f);
                                    table.setSpacingBefore(50f);
                                    table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

                                    cell = new PdfPCell(new Phrase(changeString("Dolu"), font2));
                                    cell.setColspan(3);
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table.addCell(cell);

                                    PdfPCell xercCell = new PdfPCell(new Phrase(changeString("Xərclər"), font2));
                                    xercCell.setBackgroundColor(BaseColor.GRAY);
                                    xercCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table.addCell(xercCell);
                                    PdfPCell alisCell = new PdfPCell(new Phrase(changeString("Alış"), font2));
                                    alisCell.setBackgroundColor(BaseColor.GRAY);
                                    alisCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table.addCell(alisCell);
                                    PdfPCell satisCell = new PdfPCell(new Phrase(changeString("Satış"), font2));
                                    satisCell.setBackgroundColor(BaseColor.GRAY);
                                    satisCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table.addCell(satisCell);

                                    cell = new PdfPCell(new Phrase(changeString("ADY Daşıma"), font2));
                                    cell.setRowspan(2);
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table.addCell(cell);

                                    inner = new PdfPTable(2);
                                    inner.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                                    inner.getDefaultCell().setBackgroundColor(BaseColor.GRAY);
                                    inner.addCell("MPS");
                                    inner.addCell("SPS");
                                    cell = new PdfPCell(inner);
                                    table.addCell(cell);
                                    table.addCell(cell);

                                    inner = new PdfPTable(2);
                                    inner.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                                    inner.addCell(arrayList.get(0).getBuyingMpsPrice());
                                    inner.addCell(arrayList.get(0).getBuyingSpsPrice());
                                    cell = new PdfPCell(inner);
                                    table.addCell(cell);


                                    inner = new PdfPTable(2);
                                    inner.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                                    inner.addCell(arrayList.get(0).getSellingMpsPrice());
                                    inner.addCell(arrayList.get(0).getSellingSpsPrice());
                                    cell = new PdfPCell(inner);
                                    table.addCell(cell);

                                    for (int i = 1; i < arrayList.size(); i++) {
                                        table.addCell(new Phrase(changeString(arrayList.get(i).getExpenseName()), font2));
                                        table.addCell(arrayList.get(i).getBuyyingPrice());
                                        table.addCell(arrayList.get(i).getSellingPrice());
                                    }

                                    table.addCell(new Phrase(changeString("Ümumi"), font2));

                                    inner = new PdfPTable(2);
                                    inner.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                                    inner.addCell(umumiAlisCurrentMpsTxt.getText().toString());
                                    inner.addCell(umumiAlisCurrentSpsTxt.getText().toString());
                                    cell = new PdfPCell(inner);
                                    table.addCell(cell);


                                    inner = new PdfPTable(2);
                                    inner.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                                    inner.addCell(umumiSatisCurrentMpsTxt.getText().toString());
                                    inner.addCell(umumiSatisCurrentSpsTxt.getText().toString());
                                    cell = new PdfPCell(inner);
                                    table.addCell(cell);

                                    /////////////////////////////////////////////////////////
                                    //////////////////////////////////////////////////////////

                                    PdfPTable table2 = new PdfPTable(3);

                                    PdfPCell cell2;
                                    PdfPTable inner2;

                                    table2.setSpacingAfter(30f);
                                    table2.setSpacingBefore(20f);
                                    table2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

                                    cell2 = new PdfPCell(new Phrase(changeString("Boş"), font2));
                                    cell2.setColspan(3);
                                    cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table2.addCell(cell2);

                                    PdfPCell xercCell2 = new PdfPCell(new Phrase(changeString("Xərclər"), font2));
                                    xercCell2.setBackgroundColor(BaseColor.GRAY);
                                    xercCell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table2.addCell(xercCell2);
                                    PdfPCell alisCell2 = new PdfPCell(new Phrase(changeString("Alış"), font2));
                                    alisCell2.setBackgroundColor(BaseColor.GRAY);
                                    alisCell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table2.addCell(alisCell2);
                                    PdfPCell satisCell2 = new PdfPCell(new Phrase(changeString("Satış"), font2));
                                    satisCell2.setBackgroundColor(BaseColor.GRAY);
                                    satisCell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table2.addCell(satisCell2);

                                    cell2 = new PdfPCell(new Phrase(changeString("ADY Daşıma"), font2));
                                    cell2.setRowspan(2);
                                    cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table2.addCell(cell2);

                                    inner2 = new PdfPTable(2);
                                    inner2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                                    inner2.getDefaultCell().setBackgroundColor(BaseColor.GRAY);
                                    inner2.addCell("MPS");
                                    inner2.addCell("SPS");
                                    cell2 = new PdfPCell(inner2);
                                    table2.addCell(cell2);
                                    table2.addCell(cell2);


                                    inner2 = new PdfPTable(2);
                                    inner2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                                    inner2.addCell(arrayList.get(0).getBuyingMpsPrice());
                                    inner2.addCell(arrayList.get(0).getBuyingSpsPrice());
                                    cell2 = new PdfPCell(inner2);
                                    table2.addCell(cell2);


                                    inner2 = new PdfPTable(2);
                                    inner2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                                    inner2.addCell(arrayList.get(0).getSellingMpsPrice());
                                    inner2.addCell(arrayList.get(0).getSellingSpsPrice());
                                    cell2 = new PdfPCell(inner2);
                                    table2.addCell(cell2);

                                    for (int i = 1; i < arrayList.size(); i++) {
                                        table2.addCell(new Phrase(changeString(arrayList.get(i).getExpenseName()), font2));
                                        table2.addCell(arrayList.get(i).getBuyyingPrice());
                                        table2.addCell(arrayList.get(i).getSellingPrice());
                                    }

                                    table2.addCell(new Phrase(changeString("Ümumi"), font2));

                                    inner2 = new PdfPTable(2);
                                    inner2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                                    inner2.addCell(umumiAlisCurrentMpsTxt.getText().toString());
                                    inner2.addCell(umumiAlisCurrentSpsTxt.getText().toString());
                                    cell2 = new PdfPCell(inner2);
                                    table2.addCell(cell2);


                                    inner2 = new PdfPTable(2);
                                    inner2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                                    inner2.addCell(umumiSatisCurrentMpsTxt.getText().toString());
                                    inner2.addCell(umumiSatisCurrentSpsTxt.getText().toString());
                                    cell2 = new PdfPCell(inner2);
                                    table2.addCell(cell2);

                                    ///////////////////////////////////////////////////////////////////
                                    //////////////////////////////////////////////////////////////////


                                    PdfPTable table3 = new PdfPTable(2);

                                    PdfPCell cell3;
                                    PdfPTable inner3;

                                    table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table3.setSpacingAfter(30f);
                                    cell3 = new PdfPCell(new Phrase(changeString("Yekun Qiymət"), font2));
                                    cell3.setColspan(2);
                                    cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table3.addCell(cell3);

                                    PdfPCell alisCell3 = new PdfPCell(new Phrase(changeString("Alış"), font2));
                                    alisCell3.setBackgroundColor(BaseColor.GRAY);
                                    alisCell3.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table3.addCell(alisCell3);
                                    PdfPCell satisCell3 = new PdfPCell(new Phrase(changeString("Satış"), font2));
                                    satisCell3.setBackgroundColor(BaseColor.GRAY);
                                    satisCell3.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table3.addCell(satisCell3);

                                    inner3 = new PdfPTable(2);
                                    inner3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                                    inner3.getDefaultCell().setBackgroundColor(BaseColor.GRAY);
                                    inner3.addCell("MPS");
                                    inner3.addCell("SPS");
                                    cell3 = new PdfPCell(inner3);
                                    table3.addCell(cell3);
                                    table3.addCell(cell3);

                                    inner3 = new PdfPTable(2);
                                    inner3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                                    inner3.addCell(yekunAlisCurrentMpsTxt.getText().toString());
                                    inner3.addCell(yekunAlisCurrentSpsTxt.getText().toString());
                                    cell3 = new PdfPCell(inner3);
                                    table3.addCell(cell3);


                                    inner3 = new PdfPTable(2);
                                    inner3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                                    inner3.addCell(yekunSatisCurrentMpsTxt.getText().toString());
                                    inner3.addCell(yekunSatisCurrentSpsTxt.getText().toString());
                                    cell3 = new PdfPCell(inner3);
                                    table3.addCell(cell3);


                                    PdfWriter.getInstance(document, new FileOutputStream(myFile));


                                    document.open();
                                    Paragraph tranportParagraph = new Paragraph(changeString(transportTypeTxt.getText().toString()) + ": " +
                                            changeString(firstStation.getText().toString()) + " --> "
                                            + changeString(secondSattion.getText().toString()), font1);
                                    tranportParagraph.setAlignment(Element.ALIGN_CENTER);
                                    document.add(tranportParagraph);
                                    Paragraph distanceParagraph = new Paragraph(changeString("Məsafə: ") + changeString(currentDistance.getText().toString()), font1);
                                    distanceParagraph.setAlignment(Element.ALIGN_CENTER);
                                    document.add(distanceParagraph);
                                    Paragraph qnqParagraph = new Paragraph("Qnq: " + changeString(currentQnq.getText().toString()), font1);
                                    qnqParagraph.setAlignment(Element.ALIGN_CENTER);
                                    document.add(qnqParagraph);
                                    document.add(table);
                                    document.add(table2);
                                    document.add(table3);
                                    document.close();


                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                } catch (DocumentException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                progressLyt.setVisibility(View.VISIBLE);
                                progressBar.setVisibility(View.VISIBLE);
                                sendingInformationTxt.setVisibility(View.VISIBLE);
                                sendingInformationTxt.setText("Göndərilir...");
                                doneImd.setVisibility(View.INVISIBLE);

                               // SendMail sm = new SendMail(getActivity(), emailEdt.getText().toString(), myFile, progressBar, sendingInformationTxt, doneImd, okLyt, gonderLyt, "2");
                               // sm.execute();

                            }
                        });

                        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialog) {
                                try {
                                    FileUtils.deleteDirectory(pdfFolder);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                    }
                });

            }
        });


        KeyboardVisibilityEvent.setEventListener(
                getActivity(),
                new KeyboardVisibilityEventListener() {
                    @Override
                    public void onVisibilityChanged(boolean isOpen) {
                        if(isOpen)
                        {
                            if(getActivity()!=null) {
                                changePlaceOfBUtton(73);
                                open = true;
                            }
                        }
                        else
                        {
                            if(hiddenSearchMoreLyt.getVisibility() == View.GONE) {
                                if(getActivity()!=null) {
                                    changePlaceOfBUtton(150);
                                }
                            }
                            else {
                                if (getActivity() != null) {
                                    changePlaceOfBUtton(73);
                                }
                            }

                            open = false;
                        }
                    }
                });

        return  view;
    }

    public void changePlaceOfBUtton(int dp)
    {
        float pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getActivity().getResources().getDisplayMetrics());
        final RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) calculateParentLyt.getLayoutParams();
        params.height = (int) pixels;
        calculateParentLyt.setLayoutParams(params);
    }

    public void searchDialog(final EditText editText, final ArrayList<String> arrayList)
    {
        final ArrayAdapter adapter;
        final ArrayList<String> findedResults = new ArrayList<String>();
        String find = editText.getText().toString();

        final Dialog dialog =  new Dialog(getActivity());
        View child = getActivity().getLayoutInflater().inflate(R.layout.new_custom_dialog, null);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(child);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        dialog.show();

        final EditText dialogEdtText = (EditText) dialog.findViewById(R.id.dialogEdtText);
        ListView listView = (ListView) dialog.findViewById(R.id.listView);
        ImageView closeImg = (ImageView) dialog.findViewById(R.id.closeImg);

        dialogEdtText.setText(find);

        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).contains(dialogEdtText.getText().toString())) {
                findedResults.add(arrayList.get(i));
            }

        }

        if (findedResults.isEmpty())

        {
            findedResults.add("TAPILMADI");
        }

        adapter = new ArrayAdapter<>(getActivity(), R.layout.custom_listview_item, R.id.textview, findedResults);
        listView.setAdapter(adapter);

        closeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!findedResults.get(position).equals("TAPILMADI")) {
                    dialog.dismiss();
                    editText.setText(findedResults.get(position));
                } else {
                    view.setEnabled(false);
                    view.setOnClickListener(null);
                }
            }
        });

        dialogEdtText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                findedResults.clear();

                for (int i = 0; i < arrayList.size(); i++) {
                    if (arrayList.get(i).contains(dialogEdtText.getText().toString())) {
                        findedResults.add(arrayList.get(i));
                    }
                }

                if (findedResults.isEmpty())

                {
                    findedResults.add("TAPILMADI");
                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void selectionDialog(final ArrayList<String> arrayList, final TextView textView)
    {

        ArrayAdapter adapter;
        final Dialog dialog =  new Dialog(getActivity());
        View child = getActivity().getLayoutInflater().inflate(R.layout.new_custom_dialog, null);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(child);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();

        EditText dialogEdtText = (EditText) dialog.findViewById(R.id.dialogEdtText);
        ImageView closeImg = (ImageView) dialog.findViewById(R.id.closeImg);
        dialogEdtText.setVisibility(View.GONE);

        ListView listView = (ListView) dialog.findViewById(R.id.listView);

        ViewGroup.LayoutParams params = listView.getLayoutParams();

        if (arrayList.size() < 5) {
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            listView.setLayoutParams(params);
        }


        adapter = new ArrayAdapter<>(getActivity(), R.layout.custom_listview_item, R.id.textview, arrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.dismiss();
                textView.setText(arrayList.get(position));
            }
        });


        closeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public void changeButtonColor(RelativeLayout rel1, RelativeLayout rel2, RelativeLayout rel3,
                                  TextView text1, TextView text2, TextView text3)
    {
        rel1.setBackgroundResource(R.drawable.background_calculate_lyt);
        text1.setTextColor(Color.WHITE);
        rel2.setBackgroundColor(Color.WHITE);
        text2.setTextColor(Color.parseColor("#b1b1b1"));
        rel3.setBackgroundColor(Color.WHITE);
        text3.setTextColor(Color.parseColor("#b1b1b1"));
    }

    public String changeString(String string) {

        char[] stringChar = string.toCharArray();

        for (int i = 0; i < stringChar.length; i++)

        {
            if (stringChar[i] == 'Ə') {
                stringChar[i] = 'W';
            } else if (stringChar[i] == 'ə') {
                stringChar[i] = 'w';
            }

        }

        return String.valueOf(stringChar);

    }

    @Override
    public void onResume() {
        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    // handle back button's click listener
                    Intent intent = new Intent(getActivity(), navi_menu3.class);
                    intent.putExtra("sess_user", user_);
                    intent.putExtra("sess_pass", pass_);
                    intent.putExtra("sess_name", name_);
                    intent.putExtra("sess_id", userId_);
                    intent.putStringArrayListExtra("menular", Menular.menular);
                    startActivity(intent);
                    getActivity().finish();
                    return true;
                }
                return false;
            }
        });
    }

    private class JsonParse extends AsyncTask<String, String, JSONObject>{

        @Override
        protected JSONObject doInBackground(String... strings) {
            JSONParser jsonParser = new JSONParser();
            List<BasicNameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("filter", "0"));
            params.add(new BasicNameValuePair("type", "combo"));
            params.add(new BasicNameValuePair("user", user_));
            params.add(new BasicNameValuePair("pass", pass_));

            JSONObject jsonObject = jsonParser.getJSONFromUrl(url, params);
            return jsonObject;
        }


        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);

            try {
                JSONArray dataArray = jsonObject.getJSONArray("data");
                for(int i =0; i<dataArray.length(); i++)
                {
                    JSONObject jsonObject1 = dataArray.getJSONObject(i);
                    if(jsonObject1.get("SC_TYPE").equals("ORD_TRANSTYPE"))
                    {
                        transportTypes.add(jsonObject1.getString("NAME"));
                    }
                    if(jsonObject1.get("SC_TYPE").equals("RT"))
                    {
                        wagonTypes.add(jsonObject1.getString("NAME"));
                    }
                    if(jsonObject1.get("SC_TYPE").equals("ORD_TYPEACT"))
                    {
                        rejimTypes.add(jsonObject1.getString("NAME"));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
