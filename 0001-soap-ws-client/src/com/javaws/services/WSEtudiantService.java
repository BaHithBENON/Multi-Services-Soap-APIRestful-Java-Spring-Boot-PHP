
package com.javaws.services;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

import jakarta.xml.ws.Service;
import jakarta.xml.ws.WebEndpoint;
import jakarta.xml.ws.WebServiceClient;
import jakarta.xml.ws.WebServiceException;
import jakarta.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "WSEtudiantService", targetNamespace = "http://services.javaws.com/", wsdlLocation = "http://localhost:8082/etudiants/WSEtudiant?wsdl")
public class WSEtudiantService
    extends Service
{

    private final static URL WSETUDIANTSERVICE_WSDL_LOCATION;
    private final static WebServiceException WSETUDIANTSERVICE_EXCEPTION;
    private final static QName WSETUDIANTSERVICE_QNAME = new QName("http://services.javaws.com/", "WSEtudiantService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8082/etudiants/WSEtudiant?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        WSETUDIANTSERVICE_WSDL_LOCATION = url;
        WSETUDIANTSERVICE_EXCEPTION = e;
    }

    public WSEtudiantService() {
        super(__getWsdlLocation(), WSETUDIANTSERVICE_QNAME);
    }

    public WSEtudiantService(WebServiceFeature... features) {
        super(__getWsdlLocation(), WSETUDIANTSERVICE_QNAME, features);
    }

    public WSEtudiantService(URL wsdlLocation) {
        super(wsdlLocation, WSETUDIANTSERVICE_QNAME);
    }

    public WSEtudiantService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, WSETUDIANTSERVICE_QNAME, features);
    }

    public WSEtudiantService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WSEtudiantService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns EtudiantWS
     */
    @WebEndpoint(name = "EtudiantWSPort")
    public EtudiantWS getEtudiantWSPort() {
        return super.getPort(new QName("http://services.javaws.com/", "EtudiantWSPort"), EtudiantWS.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns EtudiantWS
     */
    @WebEndpoint(name = "EtudiantWSPort")
    public EtudiantWS getEtudiantWSPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://services.javaws.com/", "EtudiantWSPort"), EtudiantWS.class, features);
    }

    private static URL __getWsdlLocation() {
        if (WSETUDIANTSERVICE_EXCEPTION!= null) {
            throw WSETUDIANTSERVICE_EXCEPTION;
        }
        return WSETUDIANTSERVICE_WSDL_LOCATION;
    }

}
