package com.example.faigy.hala;

import android.content.Context;
import android.graphics.PathEffect;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ContactFormFragment extends Fragment {

    // Declare Controls
    MainActivity mainActivity;
    private EditText inputName, inputEmail, inputPhone, inputQuestion;
    private TextInputLayout inputLayoutName, inputLayoutEmail, inputLayoutQuestion, inputLayoutPhone;
    private TextView submitButton;

    public ContactFormFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_contact_form, container, false);
        // Initialize the views for this fragment
        initializeViews(rootView);
        // set toolbar title
        //Util.setToolbarTitle(R.string.fragment_contact, mainActivity.toolbar);

        return rootView;

    }

    /**
     * Function to initialize controls
     */
    public void initializeViews(View rootView) {
        // initialize and reference controls
        inputLayoutName = (TextInputLayout) rootView.findViewById(R.id.input_layout_name);
        inputLayoutEmail = (TextInputLayout) rootView.findViewById(R.id.input_layout_email);
        inputLayoutPhone = (TextInputLayout) rootView.findViewById(R.id.input_layout_phone);
        inputLayoutQuestion = (TextInputLayout) rootView.findViewById(R.id.input_layout_question);
        inputName = (EditText) rootView.findViewById(R.id.input_name);
        inputEmail = (EditText) rootView.findViewById(R.id.input_email);
        inputPhone = (EditText) rootView.findViewById(R.id.input_phone);
        inputQuestion = (EditText) rootView.findViewById(R.id.input_question);
        submitButton = (TextView) rootView.findViewById(R.id.submit_text);
        inputName.addTextChangedListener(new MyTextWatcher(inputName));
        inputEmail.addTextChangedListener(new MyTextWatcher(inputEmail));
        inputPhone.addTextChangedListener(new MyTextWatcher(inputPhone));
        inputQuestion.addTextChangedListener(new MyTextWatcher(inputQuestion));
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });

    }

    /**
     * Validating form
     */
    private void submitForm() {
        if (!validateName()) {
            return;
        }

        if (!validateEmail()) {
            return;
        }

        if (!validatePhone()) {
            return;
        }

        if (!validateQuestion()) {
            return;
        }

        Toast.makeText(Util.getContext(), "Thank you for contacting Hala!", Toast.LENGTH_SHORT).show();
    }

    private boolean validateName() {
        if (inputName.getText().toString().trim().isEmpty()) {
            inputLayoutName.setError(getString(R.string.err_msg_name));
            requestFocus(inputName);
            return false;
        } else {
            inputLayoutName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateEmail() {
        String email = inputEmail.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            requestFocus(inputEmail);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePhone() {
        String phone = inputPhone.getText().toString().trim();

        if (phone.isEmpty() || !isValidPhone(phone)) {
            inputLayoutPhone.setError(getString(R.string.err_msg_phone));
            requestFocus(inputPhone);
            return false;
        } else {
            inputLayoutPhone.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateQuestion() {
        String question = inputQuestion.getText().toString().trim();

        if (question.isEmpty()) {
            inputLayoutQuestion.setError(getString(R.string.err_msg_question));
            requestFocus(inputQuestion);
            return false;
        } else {
            inputLayoutQuestion.setErrorEnabled(false);
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private static boolean isValidPhone(String phone) {
        String regex = "^[+]?[0-9]{10,13}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);

       return  !TextUtils.isEmpty(phone) && matcher.matches();

    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            Util.getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_name:
                    validateName();
                    break;
                case R.id.input_email:
                    validateEmail();
                    break;
                case R.id.input_phone:
                    validatePhone();
                    break;
                case R.id.input_question:
                    validateQuestion();
                    break;
            }
        }
    }

    public void onPause(){
        super.onPause();
        // remove keyboard from screen
        Util.hideSoftKeyboard();
    }
    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

}

