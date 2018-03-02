package server.devices.metadata;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeviceType {
    @SerializedName("typeId")
    private String typeId;

    @SerializedName("formalName")
    private String typeName;

    @SerializedName("synonyms")
    private List<String> synonyms = new ArrayList<String>();

    @SerializedName("capabilities")
    private Map<String, Object> m_capabilities = new HashMap<String, Object>();


    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Map<String, Object> getCapabilities() {
        return this.m_capabilities;
    }

    public void setCapbilities(Map<String, Object> capabilities) {
        this.m_capabilities.putAll(capabilities);
    }

    public List<String> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(List<String> synonyms) {
        this.synonyms = synonyms;
    }
}
