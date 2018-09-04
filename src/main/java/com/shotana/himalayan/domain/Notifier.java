package com.shotana.himalayan.domain;

import com.shotana.himalayan.domain.model.Token;

public interface Notifier {
    void send(Token token, String message);
}
