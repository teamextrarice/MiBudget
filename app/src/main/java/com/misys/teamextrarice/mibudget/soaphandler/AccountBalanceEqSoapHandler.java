package com.misys.teamextrarice.mibudget.soaphandler;

import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.kxml2.kdom.Element;
import org.kxml2.kdom.Node;

import java.util.ArrayList;

/**
 * Created by jozaldua on 7/23/2015.
 */
public class AccountBalanceEqSoapHandler {
    private String acctNum;
    private String response;
    private final String WEB_NAMESPACE = "http://webservices.bankfusion.trapedza.com";
    private final String HEAD_NAMESPACE = "http://www.misys.com/eqf/types/header";
    private final String WEB_URL = "http://manvsweqdv0003.misys.global.ad:9081/bfweb/services/EQ_CMN_readAccountDetails";
    private final String WEB_SOAP_ACTION = "http://manvsweqdv0003.misys.global.ad:9081/bfweb/services/EQ_CMN_readAccountDetailsGRF_SRV";

    public AccountBalanceEqSoapHandler(String message){
        this.acctNum = message;
    }

    public String getacctNum() {
        return acctNum;
    }

    public void setacctNum(String acctNum) {
        this.acctNum = acctNum;
    }

    public void getAcctDetails(){
        SoapObject eqReadAcct = new SoapObject(WEB_NAMESPACE,"EQ_CMN_readAccountDetailsGRF_SRV");

        SoapObject eqServiceReq = new SoapObject(WEB_NAMESPACE,"EqServiceRequest");

        SoapObject serviceData = new SoapObject("","ServiceData");
        SoapObject serviceHeader = new SoapObject("","ServiceHeader");

        //Service Data
        SoapObject acctId = new SoapObject("","accountId");

        PropertyInfo pi1 = new PropertyInfo();

        pi1.setType(PropertyInfo.STRING_CLASS);
        pi1.setName("accountRef");
        pi1.setValue(acctNum);

        PropertyInfo pi2 = new PropertyInfo();

        pi2.setType(PropertyInfo.STRING_CLASS);
        pi2.setName("accRefType");
        pi2.setValue("1");

        acctId.addProperty(pi1);
        acctId.addProperty(pi2);

        serviceData.addSoapObject(acctId);

        //Service Header
        SoapObject rqHeader = new SoapObject(HEAD_NAMESPACE,"rqHeader");

        ArrayList<PropertyInfo> props = new ArrayList<PropertyInfo>();
        for(int i = 0; i < 8; i++){
            PropertyInfo pi = new PropertyInfo();
            pi.setNamespace(HEAD_NAMESPACE);
            pi.setType(PropertyInfo.STRING_CLASS);
            props.add(pi);
        }

        props.get(0).setName("systemId");
        props.get(0).setValue("PHMKIMNH");
        props.get(1).setName("unitId");
        props.get(1).setValue("P42");
        props.get(2).setName("userIdType");
        props.get(2).setValue("1");
        props.get(3).setName("userId");
        props.get(3).setValue("superit");

        for(int i = 4; i < 8; i++){
            props.get(i).setName("responseFilter");
        }
        props.get(4).setValue("AccBasicDetail");
        props.get(5).setValue("AccBalanceDetail");
        props.get(6).setValue("AccStmtDetail");
        props.get(7).setValue("AccSpecialConditions");

        for(PropertyInfo prop: props)
            rqHeader.addProperty(prop);

        PropertyInfo optionId = new PropertyInfo();
        optionId.setNamespace(HEAD_NAMESPACE);
        optionId.setType(PropertyInfo.STRING_CLASS);
        optionId.setName("optionId");
        optionId.setValue("GRF");

        serviceHeader.addSoapObject(rqHeader);
        serviceHeader.addProperty(optionId);

        eqServiceReq.addSoapObject(serviceData);
        eqServiceReq.addSoapObject(serviceHeader);


        eqReadAcct.addSoapObject(eqServiceReq);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = false;
        envelope.setAddAdornments(false);
        envelope.implicitTypes = true;

        //Set headers
        envelope.headerOut = new Element[1];
        envelope.headerOut[0] = buildAuthHeader();
        //Set output SOAP object
        envelope.setOutputSoapObject(eqReadAcct);

        //Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(WEB_URL);

        try {
            //Invole web service
            androidHttpTransport.debug = true;
            androidHttpTransport.call(WEB_SOAP_ACTION, envelope);
            //Get the response
            SoapObject response = (SoapObject)envelope.getResponse();

            Log.d("dump Request: ", androidHttpTransport.requestDump);
            Log.d("dump response: ", androidHttpTransport.responseDump);
            //Assign it to fahren static variable
            SoapObject serviceDataRes = (SoapObject)response.getProperty(1);
            SoapObject accBasicDetail = (SoapObject)serviceDataRes.getProperty(0);
            int accBasicDetailCount = accBasicDetail.getPropertyCount();
            StringBuffer buf = new StringBuffer();
            PropertyInfo prop = null;
            for(int i = 0;i < accBasicDetailCount;i++){
                if(accBasicDetail.getProperty(i) instanceof SoapPrimitive) {
                    prop = new PropertyInfo();
                    accBasicDetail.getPropertyInfo(i, prop);
                    buf.append(prop.getName() + " = " + prop.getValue());
                    buf.append("\n");
                }
                else{
                    prop = new PropertyInfo();
                    accBasicDetail.getPropertyInfo(i, prop);
                    buf.append("<" + prop.getName() + ">");
                    buf.append("\n");
                    SoapObject accBasicObj = (SoapObject)accBasicDetail.getProperty(i);
                    for(int j = 0;j < accBasicObj.getPropertyCount();j++){
                        prop = new PropertyInfo();
                        accBasicObj.getPropertyInfo(j, prop);
                        buf.append(prop.getName() + " = " + prop.getValue());
                        buf.append("\n");
                    }
                }



            }
            this.response = buf.toString();

        } catch (Exception e) {
            Log.d("dump Request: " ,androidHttpTransport.requestDump);
            Log.d("dump response: " ,androidHttpTransport.responseDump);
            e.printStackTrace();
        }
    }

    private Element buildAuthHeader() {
        Element h = new Element().createElement(WEB_NAMESPACE, "bfgenericsoapheader");
        Element auth = new Element().createElement(WEB_NAMESPACE,"authentication");
        Element username = new Element().createElement(WEB_NAMESPACE, "userName");
        username.addChild(Node.TEXT, "superit");
        auth.addChild(Node.ELEMENT, username);
        Element pass = new Element().createElement(WEB_NAMESPACE, "password");
        pass.addChild(Node.TEXT, "superit");
        auth.addChild(Node.ELEMENT, pass);
        h.addChild(Node.ELEMENT,auth);
        return h;
    }

}
