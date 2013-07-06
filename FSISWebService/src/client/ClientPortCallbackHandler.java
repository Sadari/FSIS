   package client;

    public abstract class ClientPortCallbackHandler{

    protected Object clientData;

    public ClientPortCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    public ClientPortCallbackHandler(){
        this.clientData = null;
    }

    public Object getClientData() {
        return clientData;
     }

        
           public void receiveResultfindFS(
                    client.ClientPortStub.FindFSResponse result
                        ) {
           }

           public void receiveErrorfindFS(java.lang.Exception e) {
            }
                
               
    }
    
