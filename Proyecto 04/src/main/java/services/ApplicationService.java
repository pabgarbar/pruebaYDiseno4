
package services;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.ApplicationRepository;
import domain.Application;
import domain.FixUpTask;
import domain.HandyWorker;
import domain.Status;

@Service
@Transactional
public class ApplicationService {

	// Managed repository ------------------------------------------

	@Autowired
	private ApplicationRepository	applicationRepository;


	// Supporting Services ------------------------------------------

	public static Application createApplication(final Integer offeredPrice, final List<String> comments, final FixUpTask fixUpTask, final HandyWorker handyWorker) {

		final Application result = new Application();
		final Date thisMoment = new Date();

		thisMoment.setTime(thisMoment.getTime() - 1);

		result.setComments(comments);
		result.setFixUpTask(fixUpTask);
		result.setHandyWorker(handyWorker);
		result.setMoment(thisMoment);
		result.setOfferedPrice(offeredPrice);
		result.setStatus(Status.PENDING);

		return result;
	}

	//Simple CRUD methods

	public Application create(final Application application) {
		this.applicationRepository.save(application);
		return application;
	}

	public Collection<Application> findAll() {
		Collection<Application> result;

		result = this.applicationRepository.findAll();

		return result;
	}

	public Application findOne(final Integer id) {

		final Application result = this.applicationRepository.findOne(id);
		return result;
	}

	public Application saveApplication(final Application application) {
		return this.applicationRepository.save(application);
	}

	public void delete(final Application applitacion) {
		this.applicationRepository.delete(applitacion);
	}
}