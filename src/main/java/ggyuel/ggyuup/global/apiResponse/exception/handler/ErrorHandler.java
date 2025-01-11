package ggyuel.ggyuup.global.apiResponse.exception.handler;


import ggyuel.ggyuup.global.apiResponse.code.BaseErrorCode;
import ggyuel.ggyuup.global.apiResponse.exception.GeneralException;

public class ErrorHandler extends GeneralException {

    public ErrorHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
