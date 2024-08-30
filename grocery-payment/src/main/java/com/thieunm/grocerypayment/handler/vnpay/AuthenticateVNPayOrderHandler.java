package com.thieunm.grocerypayment.handler.vnpay;

import com.thieunm.grocerybase.cqrs.query.QueryHandler;
import com.thieunm.grocerypayment.dto.request.vnpay.AuthenticateVNPayOrderRequest;
import com.thieunm.grocerypayment.dto.response.vnpay.AuthenticateVNPayOrderResponse;
import com.thieunm.grocerypayment.utils.VNPayUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticateVNPayOrderHandler extends QueryHandler<AuthenticateVNPayOrderRequest, AuthenticateVNPayOrderResponse> {

    private final VNPayUtil vnPayUtil;

    @Override
    public AuthenticateVNPayOrderResponse handle(AuthenticateVNPayOrderRequest requestData) {
        Map<String, String> fields = new HashMap<>();
        HttpServletRequest request = requestData.getHttpServletRequest();
        for (Enumeration<String> params = request.getParameterNames(); params.hasMoreElements(); ) {
            String fieldName = URLEncoder.encode(params.nextElement(), StandardCharsets.US_ASCII);
            String fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII);
            if ((fieldValue != null) && (!fieldValue.isEmpty())) {
                fields.put(fieldName, fieldValue);
            }
        }

        String vnp_SecureHash = request.getParameter("vnp_SecureHash");
        fields.remove("vnp_SecureHashType");
        fields.remove("vnp_SecureHash");
        String signValue = vnPayUtil.hashAllFields(fields);
        if (signValue.equals(vnp_SecureHash) && "00".equals(request.getParameter("vnp_TransactionStatus"))) {
            return new AuthenticateVNPayOrderResponse(VNPayUtil.VNPAY_ORDER_SUCCESS);
        }
        return new AuthenticateVNPayOrderResponse(VNPayUtil.VNPAY_ORDER_FAILED);
    }
}
