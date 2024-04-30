package com.dprsnn.UtilPlast.services;

import com.dprsnn.UtilPlast.models.Person;
import com.dprsnn.UtilPlast.models.PlasticCategory;
import com.dprsnn.UtilPlast.models.PointAddress;
import com.dprsnn.UtilPlast.models.Request;
import com.dprsnn.UtilPlast.repositories.AddressRepository;
import com.dprsnn.UtilPlast.repositories.CategoryRepository;
import com.dprsnn.UtilPlast.repositories.PeopleRepository;
import com.dprsnn.UtilPlast.repositories.RequestRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class RequestService {
    private final RequestRepository requestRepository;
    private final CategoryRepository categoryRepository;
    private final AddressRepository addressRepository;
    private final PeopleRepository peopleRepository;

    public List<Request> requestList(){
        return requestRepository.findAll();
    }
    public RequestService(RequestRepository requestRepository, CategoryRepository categoryRepository, AddressRepository addressRepository, PeopleRepository peopleRepository) {
        this.requestRepository = requestRepository;
        this.categoryRepository = categoryRepository;
        this.addressRepository = addressRepository;
        this.peopleRepository = peopleRepository;
    }

    public void createRequest(Long category, Long address, String amount, String comment, Person person) {
        Request request = new Request();
        request.setPerson(person);
        request.setPointAdress(addressRepository.getPointAddressById(address));
        request.setPlasticCategory(categoryRepository.findPlasticCategoryById(category));
        request.setAmount(amount);
        request.setComment(comment);
        request.setStatus("Pending");
        request.setRequestCode(generateCode(8));

        requestRepository.save(request);

    }

    public static String generateCode(int length) {
        Random random = new Random();
        StringBuilder codeBuilder = new StringBuilder();

        // Генеруємо випадкові цифри та додаємо їх до рядка
        for (int i = 0; i < length; i++) {
            int digit = random.nextInt(10); // Випадкове число від 0 до 9
            codeBuilder.append(digit);
        }

        // Додаємо нулі зліва, якщо довжина рядка менша за необхідну
        while (codeBuilder.length() < length) {
            codeBuilder.insert(0, "0");
        }

        return codeBuilder.toString();
    }

    public List<Request> getAdminRequests(String keyword, String status) {
        List<Request> requestList = new ArrayList<>();

        if(keyword == null && status == null ){
            requestList = requestRepository.findAll();
        } else if (keyword.equals("") && status == null) {
            requestList = requestRepository.findAll();
        } else if (keyword.equals("") && status != null) {
            requestList = requestRepository.findAllByStatus(status);
        } else if (!keyword.equals("") && status == null) {
            List<Person> personList = peopleRepository.findAllByEmailOrNameOrSurnameOrPhoneNumber(keyword, keyword, keyword, keyword);

            for (int i = 0; i < personList.size(); i++) {
                requestList.addAll(personList.get(i).getRequestList());
            }
        } else if (!keyword.equals("") && status != null) {
            List<Person> personList = peopleRepository.findAllByEmailOrNameOrSurnameOrPhoneNumber(keyword, keyword, keyword, keyword);

            for (int i = 0; i < personList.size(); i++) {
                for (int j = 0; j < personList.get(i).getRequestList().size(); j++) {
                    Request request = personList.get(i).getRequestList().get(j);
                    if(request.getStatus().equals(status)) requestList.add(request);
                }
            }

        }

        if (requestList.isEmpty()){
            requestList = requestRepository.findAllByRequestCode(keyword);
        }

        return requestList;
    }

    public Request getRequestById(Long id) {
        return requestRepository.findRequestById(id);
    }

    public void saveEditedRequest(Long id, Long category, Long address, String amount, String comment) {
        Request request = requestRepository.findRequestById(id);
        PointAddress pointAddress = addressRepository.getPointAddressById(address);
        PlasticCategory plasticCategory = categoryRepository.findPlasticCategoryById(category);

        request.setPointAdress(pointAddress);
        request.setPlasticCategory(plasticCategory);
        request.setAmount(amount);
        request.setComment(comment);

        requestRepository.save(request);
    }

    public void deleteRequest(Long id) {
        requestRepository.deleteById(id);
    }

    public void saveEditedStatus(Long id, String status) {
        Request request = requestRepository.findRequestById(id);
        request.setStatus(status);

        requestRepository.save(request);
    }
}
