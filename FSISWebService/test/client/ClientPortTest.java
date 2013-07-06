

/**
 * ClientPortTest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1  Built on : Aug 31, 2011 (12:22:40 CEST)
 */
    package client;

    /*
     *  ClientPortTest Junit test case
    */

    public class ClientPortTest extends junit.framework.TestCase{

     
        /**
         * Auto generated test method
         */
        public  void testfindFS() throws java.lang.Exception{

        client.ClientPortStub stub =
                    new client.ClientPortStub();//the default implementation should point to the right endpoint

           client.ClientPortStub.FindFS findFS6=
                                                        (client.ClientPortStub.FindFS)getTestObject(client.ClientPortStub.FindFS.class);
                    // TODO : Fill in the findFS6 here
                findFS6.setCordinations("345 45");
                        assertNotNull(stub.findFS(
                        findFS6));
                  



        }
        public  void testfindFS2() throws java.lang.Exception{

            client.ClientPortStub stub =
                        new client.ClientPortStub();//the default implementation should point to the right endpoint

               client.ClientPortStub.FindFS findFS6=
                                                            (client.ClientPortStub.FindFS)getTestObject(client.ClientPortStub.FindFS.class);
                        // TODO : Fill in the findFS6 here
                    findFS6.setCordinations("34.5 45");
                            assertNotNull(stub.findFS(
                            findFS6));
                      



            }
        public  void testfindFS3() throws java.lang.Exception{

            client.ClientPortStub stub =
                        new client.ClientPortStub();//the default implementation should point to the right endpoint

               client.ClientPortStub.FindFS findFS6=
                                                            (client.ClientPortStub.FindFS)getTestObject(client.ClientPortStub.FindFS.class);
                        // TODO : Fill in the findFS6 here
                    findFS6.setCordinations("3.5 4.5");
                            assertNotNull(stub.findFS(
                            findFS6));
                      



            }
        
         /**
         * Auto generated test method
         */
        public  void testStartfindFS() throws java.lang.Exception{
            client.ClientPortStub stub = new client.ClientPortStub();
             client.ClientPortStub.FindFS findFS6=
                                                        (client.ClientPortStub.FindFS)getTestObject(client.ClientPortStub.FindFS.class);
                    // TODO : Fill in the findFS6 here
                

                stub.startfindFS(
                         findFS6,
                    new tempCallbackN65548()
                );
              


        }

        private class tempCallbackN65548  extends client.ClientPortCallbackHandler{
            public tempCallbackN65548(){ super(null);}

            public void receiveResultfindFS(
                         client.ClientPortStub.FindFSResponse result
                            ) {
                
            }

            public void receiveErrorfindFS(java.lang.Exception e) {
                fail();
            }

        }
      
          /**
          * Auto generated test method
          */
          public  void testchangeDiameter() throws java.lang.Exception{

          client.ClientPortStub stub =
          new client.ClientPortStub();//the default implementation should point to the right endpoint
          client.ClientPortStub.ChangeDiameter changeDiameter8=
                  (client.ClientPortStub.ChangeDiameter)getTestObject(client.ClientPortStub.ChangeDiameter.class);
                      // TODO : Fill in the changeDiameter8 here
                  

                  //There is no output to be tested!
                  stub.changeDiameter(
                  changeDiameter8);

              
          }
      
        //Create an ADBBean and provide it as the test object
        public org.apache.axis2.databinding.ADBBean getTestObject(java.lang.Class type) throws java.lang.Exception{
           return (org.apache.axis2.databinding.ADBBean) type.newInstance();
        }

        
        

    }
    