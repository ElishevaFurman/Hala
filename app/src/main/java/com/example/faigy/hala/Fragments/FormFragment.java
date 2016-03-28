package com.example.faigy.hala.Fragments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.faigy.hala.Activities.MainActivity;
import com.example.faigy.hala.Classes.MySingleton;
import com.example.faigy.hala.R;
import com.example.faigy.hala.Utilities.Util;
import com.example.faigy.hala.Utilities.GMailSender;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormFragment extends Fragment {

    // Declare Activities
    MainActivity mainActivity;

    // Declare Controls
    private EditText inputName, inputEmail, inputPhone, inputQuestion;
    private TextInputLayout inputLayoutName, inputLayoutEmail, inputLayoutQuestion, inputLayoutPhone;
    private TextView submitButton;
    RelativeLayout relativeLayout;
    String lastFragment;

    // Declare Variables
    String getMessage;
    boolean ignoreNextTextChangeName = false;
    boolean ignoreNextTextChangeEmail = false;
    boolean ignoreNextTextChangePhone = false;
    boolean ignoreNextTextChangeMessage = false;

    public FormFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_form, container, false);
        lastFragment =  MySingleton.getInstance().getLastFragment();
        // Initialize the views for this fragment
        initializeViews(rootView);

        return rootView;

    }

    /**
     * Function to initialize controls
     */
    public void initializeViews(View rootView) {
        // initialize and reference controls
        relativeLayout = (RelativeLayout) rootView.findViewById(R.id
                .coordinatorLayout);
        inputLayoutName = (TextInputLayout) rootView.findViewById(R.id.input_layout_name);
        inputLayoutEmail = (TextInputLayout) rootView.findViewById(R.id.input_layout_email);
        inputLayoutPhone = (TextInputLayout) rootView.findViewById(R.id.input_layout_phone);
        inputLayoutQuestion = (TextInputLayout) rootView.findViewById(R.id.input_layout_question);
        inputName = (EditText) rootView.findViewById(R.id.input_name);
        inputEmail = (EditText) rootView.findViewById(R.id.input_email);
        inputPhone = (EditText) rootView.findViewById(R.id.input_phone);
        inputQuestion = (EditText) rootView.findViewById(R.id.input_question);
        submitButton = (TextView) rootView.findViewById(R.id.submit_text);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });

        getMessage = inputQuestion.getText().toString();
    }

    public void onResume(){
        super.onResume();

        if (lastFragment.equals("faqFragment")){
            Util.setToolbarTitle(R.string.fragment_ask, mainActivity.toolbar);
            Util.enableBackButton(R.drawable.ic_arrow_back_24dp, mainActivity.toolbar, mainActivity.drawer);
        }

        // removed textWatchers from views
        removeTextWatcherFromViews();
        // clear all views
        clearTextViews();
        // set focus on inputName
        inputName.requestFocus();

        inputName.addTextChangedListener(new MyTextWatcher(inputName));
        inputEmail.addTextChangedListener(new MyTextWatcher(inputEmail));
        inputPhone.addTextChangedListener(new MyTextWatcher(inputPhone));
        inputQuestion.addTextChangedListener(new MyTextWatcher(inputQuestion));


    }


    /**
     * Validating form
     */
    private void submitForm() {
        // if validateName is false
        if (!validateName()) {
            return;
        }
        // if validateEmail is false
        if (!validateEmail()) {
            return;
        }
        // if validatePhone is false
        if (!validatePhone()) {
            return;
        }
        // if validateQuestion is false
        if (!validateQuestion()) {
            return;
        }

        // execute SendMailTask and send values to constructor
        new SendMailTask(inputName.getText().toString(), inputEmail.getText().toString(),
                inputPhone.getText().toString(), inputQuestion.getText().toString()).execute();

    }

    /**
     * Function to clear the text in all editTexts
     */
    public void clearTextViews() {
        // clear editTexts
        inputName.setText("");
        inputEmail.setText("");
        inputPhone.setText("");
        inputQuestion.setText("");
    }

    /**
     * Function to remove textWatcher from views
     */
    public void removeTextWatcherFromViews() {
        // set booleans to true
        ignoreNextTextChangeName = true;
        ignoreNextTextChangeEmail = true;
        ignoreNextTextChangePhone = true;
        ignoreNextTextChangeMessage = true;
    }

    /**
     * Function to validate name
     */
    private boolean validateName() {
        // if inputName is empty
        if (inputName.getText().toString().trim().isEmpty()) {
            // show error in the inputLayoutName
            inputLayoutName.setError(getString(R.string.err_msg_name));
            // requestFocus on inputName
            requestFocus(inputName);
            return false;
        } else {
            // set errorEnabled to false
            inputLayoutName.setErrorEnabled(false);
        }

        return true;
    }

    /**
     * Function to validate email
     */
    private boolean validateEmail() {
        // declare email variable and assign inputEmail value to it
        String email = inputEmail.getText().toString().trim();
        // if email is empty
        if (email.isEmpty() || !isValidEmail(email)) {
            // show error in the inputLayoutEmail
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            // requestFocus on inputEmail
            requestFocus(inputEmail);
            return false;
        } else {
            // set errorEnabled to false
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    /**
     * Function to validate phone
     */
    private boolean validatePhone() {
        // declare phone variable and assign inputPhone value to it
        String phone = inputPhone.getText().toString().trim();
        // if phone is empty or phone is not valid
        if (phone.isEmpty() || !isValidPhone(phone)) {
            // show error in the inputLayoutEmail
            inputLayoutPhone.setError(getString(R.string.err_msg_phone));
            // requestFocus on inputEmail
            requestFocus(inputPhone);
            return false;
        } else {
            // set errorEnabled to false
            inputLayoutPhone.setErrorEnabled(false);
        }

        return true;
    }

    /**
     * Function to validate question
     */
    private boolean validateQuestion() {
        // declare question variable and assign inputQuestion value to it
        String question = inputQuestion.getText().toString().trim();
        // if question is empty or phone is not valid
        if (question.isEmpty()) {
            // show error in the inputLayoutQuestion
            inputLayoutQuestion.setError(getString(R.string.err_msg_question));
            // requestFocus on inputQuestion
            requestFocus(inputQuestion);
            return false;
        } else {
            // set errorEnabled to false
            inputLayoutQuestion.setErrorEnabled(false);
        }

        return true;
    }

    /**
     * Function to check if email is valid or not
     *
     * @param email - email address entered
     */
    private static boolean isValidEmail(String email) {
        // return if email is valid or not
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /**
     * Function to check if phone is valid or not
     *
     * @param phone - phone address entered
     */
    private static boolean isValidPhone(String phone) {
        // declare string of regex
        String regex = "^[+]?[0-9]{9,13}$";
        // instaniate a pattern and compile with regex
        Pattern pattern = Pattern.compile(regex);
        // instaniate a matcher and check if phone matches
        Matcher matcher = pattern.matcher(phone);
        // return if phone is valid or not
        return !TextUtils.isEmpty(phone) && matcher.matches();

    }

    /**
     * Function that requests the focus on a view
     *
     * @param view - view to set the focus on
     */
    private void requestFocus(View view) {
        // if view.requestFocus is true
        if (view.requestFocus()) {
            // request focus on that view
            Util.getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    /**
     * TextWatcher - for all EditText controls
     */
    private class MyTextWatcher implements TextWatcher {
        // declare controls
        private View view;

        // constructor
        private MyTextWatcher(View view) {
            this.view = view;
        }

        /**
         * This method is called when text is about to be replaced by new text
         */
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        /**
         * This method is called when text have just replaced old text.
         */
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        /**
         * This method is called when text has been changed.
         */
        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                // if the case is input_name
                case R.id.input_name:
                    // if ignoreNextTextChangeName is true
                    if (ignoreNextTextChangeName) {
                        // set ignoreNextTextChangeName to false
                        ignoreNextTextChangeName = false;
                        return;
                        // if ignoreNextTextChangeName is false
                    } else {
                        // validate name
                        validateName();
                        break;
                    }
                    // if the case is input_email
                case R.id.input_email:
                    // if ignoreNextTextChangeEmail is true
                    if (ignoreNextTextChangeEmail) {
                        // set ignoreNextTextChangeEmail to false
                        ignoreNextTextChangeEmail = false;
                        return;
                        // if ignoreNextTextChangeEmail is false
                    } else {
                        // validate email
                        validateEmail();
                        break;
                    }
                    // if the case is input_phone
                case R.id.input_phone:
                    // if ignoreNextTextChangePhone is true
                    if (ignoreNextTextChangePhone) {
                        // set ignoreNextTextChangePhone to false
                        ignoreNextTextChangePhone = false;
                        return;
                        // if ignoreNextTextChangePhone is false
                    } else {
                        // validate phone
                        validatePhone();
                        break;
                    }
                    // if the case is input_question
                case R.id.input_question:
                    // if ignoreNextTextChangeMessage is true
                    if (ignoreNextTextChangeMessage) {
                        // set ignoreNextTextChangeMessage to false
                        ignoreNextTextChangeMessage = false;
                        return;
                        // if ignoreNextTextChangeMessage is false
                    } else {
                        // validate question
                        validateQuestion();
                        break;
                    }
            }
        }
    }

    public void onPause() {
        super.onPause();
        // remove keyboard from screen
        Util.hideSoftKeyboard();
        // removed textWatchers from views
        removeTextWatcherFromViews();
        // clear all views
        clearTextViews();
        // set focus on inputName
        inputName.requestFocus();

    }

    /**
     * Function to set fragment to this main activity
     *
     * @param mainActivity - set main activity
     */
    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    /**
     * Background Async Task to send email
     */
    private class SendMailTask extends AsyncTask<Void, Void, Void> {
        // declare variables
        private String name, email, phone, message;
        Boolean success = false;

        // declare controls
        private ProgressDialog progressDialog;

        // consturctor
        public SendMailTask(String name, String email, String phone, String message) {
            this.name = name;
            this.email = email;
            this.phone = phone;
            this.message = message;
        }

        /**
         * Before starting background thread
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // show progress dialog
            progressDialog = ProgressDialog.show(Util.getActivity(), "Please wait", "Sending mail", true, false);

        }

        /**
         * Sending email
         *
         * @param aVoid
         */
        @Override
        protected Void doInBackground(Void... aVoid) {
            try {
                // instantiate sender and send params (values from editTexts)
                GMailSender sender = new GMailSender("le7friedman@gmail.com", "100508701");
                sender.sendMail("Contact Form From Hala App",
                        "Name: " + name + "\n" +
                                "Phone: " + phone + "\n" +
                                "Message: " + message,
                        email,
                        "le7friedman@gmail.com");
                // set success to true
                success = true;
            } catch (Exception e) {
                Log.e("SendMail", e.getMessage(), e);
                // set success to false
                success = false;
            }

            return null;
        }

        /**
         * After completing background task
         *
         * @param aVoid
         **/
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            // dismiss the dialog once done
            progressDialog.dismiss();
            // getSuccess result from MySinglton class and assign it to success boolean
            Boolean successSent = MySingleton.getInstance().getSuccess();
            // if email was sent successfully
            if (success && successSent) {
                // show message in Snackbar - "Email was sent to Hala"
                Snackbar.make(relativeLayout, "Email sent to Hala", Snackbar.LENGTH_LONG).show();
                // removed textWatchers from views
                removeTextWatcherFromViews();
                // clear all views
                clearTextViews();
                // set focus on inputName
                inputName.requestFocus();
            } else {
                // show message in Snackbar - "Email failed to send to Hala"
                Snackbar.make(relativeLayout, "Email failed to send to Hala", Snackbar.LENGTH_LONG).show();
            }

        }
    }

}
