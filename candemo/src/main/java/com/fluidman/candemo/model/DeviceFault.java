
package com.fluidman.candemo.model;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DeviceFault {

    
    private String deviceId;
    
    private java.util.List<String> faults;
    
    private String status;
    

    public DeviceFault () {
    }

    
    
    @JsonProperty("deviceId")
    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
    
    
    
    @JsonProperty("faults")
    public java.util.List<String> getFaults() {
        return faults;
    }

    public void setFaults(java.util.List<String> faults) {
        this.faults = faults;
    }
    
    
    
    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DeviceFault DeviceFault = (DeviceFault) o;

        return Objects.equals(deviceId, DeviceFault.deviceId) &&
        Objects.equals(faults, DeviceFault.faults) &&
        
        Objects.equals(status, DeviceFault.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deviceId, faults,  status);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DeviceFault {\n");
        
        sb.append("    deviceId: ").append(toIndentedString(deviceId)).append("\n");
        sb.append("    faults: ").append(toIndentedString(faults)).append("\n");
        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
