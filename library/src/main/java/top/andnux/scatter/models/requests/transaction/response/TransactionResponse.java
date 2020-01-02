package top.andnux.scatter.models.requests.transaction.response;

import java.util.Arrays;

public class TransactionResponse {
    private String[] signatures;
    private ReturnedFields returnedFields;

    public TransactionResponse(String[] signatures, ReturnedFields returnedFields) {
        this.signatures = signatures;
        this.returnedFields = returnedFields;
    }

    @Override
    public String toString() {
        return "TransactionResponse{" +
                "signatures=" + Arrays.toString(signatures) +
                ", returnedFields=" + returnedFields +
                '}';
    }

    public String[] getSignatures() {
        return signatures;
    }

    public void setSignatures(String[] signatures) {
        this.signatures = signatures;
    }

    public ReturnedFields getReturnedFields() {
        return returnedFields;
    }

    public void setReturnedFields(ReturnedFields returnedFields) {
        this.returnedFields = returnedFields;
    }
}